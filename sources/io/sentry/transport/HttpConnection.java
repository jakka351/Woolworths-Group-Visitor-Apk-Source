package io.sentry.transport;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import io.sentry.RequestDetails;
import io.sentry.SentryEnvelope;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.lang3.CharEncoding;

/* loaded from: classes3.dex */
final class HttpConnection {
    private static final Charset UTF_8 = Charset.forName(CharEncoding.UTF_8);
    private final SentryOptions options;
    private final Proxy proxy;
    private final RateLimiter rateLimiter;
    private final RequestDetails requestDetails;

    private boolean isSuccessfulResponseCode(int i) {
        return i == 200;
    }

    public HttpConnection(SentryOptions sentryOptions, RequestDetails requestDetails, RateLimiter rateLimiter) {
        this(sentryOptions, requestDetails, AuthenticatorWrapper.getInstance(), rateLimiter);
    }

    HttpConnection(SentryOptions sentryOptions, RequestDetails requestDetails, AuthenticatorWrapper authenticatorWrapper, RateLimiter rateLimiter) {
        this.requestDetails = requestDetails;
        this.options = sentryOptions;
        this.rateLimiter = rateLimiter;
        Proxy proxyResolveProxy = resolveProxy(sentryOptions.getProxy());
        this.proxy = proxyResolveProxy;
        if (proxyResolveProxy == null || sentryOptions.getProxy() == null) {
            return;
        }
        String user = sentryOptions.getProxy().getUser();
        String pass = sentryOptions.getProxy().getPass();
        if (user == null || pass == null) {
            return;
        }
        authenticatorWrapper.setDefault(new ProxyAuthenticator(user, pass));
    }

    private Proxy resolveProxy(SentryOptions.Proxy proxy) {
        Proxy.Type type;
        if (proxy != null) {
            String port = proxy.getPort();
            String host = proxy.getHost();
            if (port != null && host != null) {
                try {
                    if (proxy.getType() != null) {
                        type = proxy.getType();
                    } else {
                        type = Proxy.Type.HTTP;
                    }
                    return new Proxy(type, new InetSocketAddress(host, Integer.parseInt(port)));
                } catch (NumberFormatException e) {
                    this.options.getLogger().log(SentryLevel.ERROR, e, "Failed to parse Sentry Proxy port: " + proxy.getPort() + ". Proxy is ignored", new Object[0]);
                }
            }
        }
        return null;
    }

    HttpURLConnection open() throws IOException {
        URLConnection uRLConnectionOpenConnection;
        if (this.proxy == null) {
            uRLConnectionOpenConnection = this.requestDetails.getUrl().openConnection();
        } else {
            uRLConnectionOpenConnection = this.requestDetails.getUrl().openConnection(this.proxy);
        }
        return (HttpURLConnection) uRLConnectionOpenConnection;
    }

    private HttpURLConnection createConnection() throws IOException {
        HttpURLConnection httpURLConnectionOpen = open();
        for (Map.Entry<String, String> entry : this.requestDetails.getHeaders().entrySet()) {
            httpURLConnectionOpen.setRequestProperty(entry.getKey(), entry.getValue());
        }
        httpURLConnectionOpen.setRequestMethod("POST");
        httpURLConnectionOpen.setDoOutput(true);
        httpURLConnectionOpen.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnectionOpen.setRequestProperty("Content-Type", "application/x-sentry-envelope");
        httpURLConnectionOpen.setRequestProperty("Accept", "application/json");
        httpURLConnectionOpen.setRequestProperty("Connection", "close");
        httpURLConnectionOpen.setConnectTimeout(this.options.getConnectionTimeoutMillis());
        httpURLConnectionOpen.setReadTimeout(this.options.getReadTimeoutMillis());
        SSLSocketFactory sslSocketFactory = this.options.getSslSocketFactory();
        if ((httpURLConnectionOpen instanceof HttpsURLConnection) && sslSocketFactory != null) {
            ((HttpsURLConnection) httpURLConnectionOpen).setSSLSocketFactory(sslSocketFactory);
        }
        httpURLConnectionOpen.connect();
        return httpURLConnectionOpen;
    }

    public TransportResult send(SentryEnvelope sentryEnvelope) throws IOException {
        try {
            OutputStream outputStream = createConnection().getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    this.options.getSerializer().serialize(sentryEnvelope, gZIPOutputStream);
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } finally {
                }
            } finally {
            }
        } finally {
            try {
                return readAndLog(r0);
            } finally {
            }
        }
        return readAndLog(r0);
    }

    private TransportResult readAndLog(HttpURLConnection httpURLConnection) {
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                updateRetryAfterLimits(httpURLConnection, responseCode);
                if (isSuccessfulResponseCode(responseCode)) {
                    this.options.getLogger().log(SentryLevel.DEBUG, "Envelope sent successfully.", new Object[0]);
                    return TransportResult.success();
                }
                this.options.getLogger().log(SentryLevel.ERROR, "Request failed, API returned %s", Integer.valueOf(responseCode));
                if (this.options.isDebug()) {
                    this.options.getLogger().log(SentryLevel.ERROR, "%s", getErrorMessageFromStream(httpURLConnection));
                }
                return TransportResult.error(responseCode);
            } catch (IOException e) {
                this.options.getLogger().log(SentryLevel.ERROR, e, "Error reading and logging the response stream", new Object[0]);
                closeAndDisconnect(httpURLConnection);
                return TransportResult.error();
            }
        } finally {
            closeAndDisconnect(httpURLConnection);
        }
    }

    public void updateRetryAfterLimits(HttpURLConnection httpURLConnection, int i) {
        String headerField = httpURLConnection.getHeaderField("Retry-After");
        this.rateLimiter.updateRetryAfterLimits(httpURLConnection.getHeaderField("X-Sentry-Rate-Limits"), headerField, i);
    }

    private void closeAndDisconnect(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.getInputStream().close();
        } catch (IOException unused) {
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
        httpURLConnection.disconnect();
    }

    private String getErrorMessageFromStream(HttpURLConnection httpURLConnection) throws IOException {
        try {
            InputStream errorStream = httpURLConnection.getErrorStream();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, UTF_8));
                try {
                    StringBuilder sb = new StringBuilder();
                    boolean z = true;
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (!z) {
                            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        }
                        sb.append(line);
                        z = false;
                    }
                    String string = sb.toString();
                    bufferedReader.close();
                    if (errorStream != null) {
                        errorStream.close();
                    }
                    return string;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            return "Failed to obtain error message while analyzing send failure.";
        }
    }

    Proxy getProxy() {
        return this.proxy;
    }
}
