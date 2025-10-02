package org.maplibre.android.maps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.maplibre.android.MapLibre;
import org.maplibre.android.MapStrictMode;
import org.maplibre.android.R;
import org.maplibre.android.attribution.Attribution;
import org.maplibre.android.attribution.AttributionParser;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.style.sources.Source;

/* loaded from: classes2.dex */
public class AttributionDialogManager implements View.OnClickListener, DialogInterface.OnClickListener {
    private static final String MAP_FEEDBACK_STYLE_URI_REGEX = "^(.*://[^:^/]*)/(.*)/(.*)";
    private static final String MAP_FEEDBACK_URL = "https://apps.mapbox.com/feedback";
    private static final String MAP_FEEDBACK_URL_LOCATION_FRAGMENT_FORMAT = "/%f/%f/%f/%f/%d";
    private static final String MAP_FEEDBACK_URL_OLD = "https://www.mapbox.com/map-feedback";
    private Set<Attribution> attributionSet;
    private final Context context;
    private AlertDialog dialog;
    private final MapLibreMap maplibreMap;

    public AttributionDialogManager(Context context, MapLibreMap mapLibreMap) {
        this.context = context;
        this.maplibreMap = mapLibreMap;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.attributionSet = new AttributionBuilder(this.maplibreMap, view.getContext()).build();
        Context context = this.context;
        if (context instanceof Activity ? ((Activity) context).isFinishing() : false) {
            return;
        }
        showAttributionDialog(getAttributionTitles());
    }

    protected void showAttributionDialog(String[] strArr) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(R.string.maplibre_attributionsDialogTitle);
        builder.setAdapter(new ArrayAdapter(this.context, R.layout.maplibre_attribution_list_item, strArr), this);
        this.dialog = builder.show();
    }

    private String[] getAttributionTitles() {
        ArrayList arrayList = new ArrayList();
        Iterator<Attribution> it = this.attributionSet.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getTitle());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        showMapAttributionWebPage(i);
    }

    public void onStop() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.dialog.dismiss();
    }

    private boolean isLatestEntry(int i) {
        return i == getAttributionTitles().length - 1;
    }

    private void showMapAttributionWebPage(int i) {
        Set<Attribution> set = this.attributionSet;
        String url = ((Attribution[]) set.toArray(new Attribution[set.size()]))[i].getUrl();
        if (url.contains(MAP_FEEDBACK_URL_OLD) || url.contains(MAP_FEEDBACK_URL)) {
            url = buildMapFeedbackMapUrl(MapLibre.getApiKey());
        }
        showWebPage(url);
    }

    String buildMapFeedbackMapUrl(String str) {
        Uri.Builder builderBuildUpon = Uri.parse(MAP_FEEDBACK_URL).buildUpon();
        CameraPosition cameraPosition = this.maplibreMap.getCameraPosition();
        if (cameraPosition != null) {
            builderBuildUpon.encodedFragment(String.format(Locale.getDefault(), MAP_FEEDBACK_URL_LOCATION_FRAGMENT_FORMAT, Double.valueOf(cameraPosition.target.getLongitude()), Double.valueOf(cameraPosition.target.getLatitude()), Double.valueOf(cameraPosition.zoom), Double.valueOf(cameraPosition.bearing), Integer.valueOf((int) cameraPosition.tilt)));
        }
        String packageName = this.context.getApplicationContext().getPackageName();
        if (packageName != null) {
            builderBuildUpon.appendQueryParameter("referrer", packageName);
        }
        if (str != null) {
            builderBuildUpon.appendQueryParameter("access_token", str);
        }
        Style style = this.maplibreMap.getStyle();
        if (style != null) {
            Matcher matcher = Pattern.compile(MAP_FEEDBACK_STYLE_URI_REGEX).matcher(style.getUri());
            if (matcher.find()) {
                String strGroup = matcher.group(2);
                builderBuildUpon.appendQueryParameter("owner", strGroup).appendQueryParameter("id", matcher.group(3));
            }
        }
        return builderBuildUpon.build().toString();
    }

    private void showWebPage(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            this.context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this.context, R.string.maplibre_attributionErrorNoBrowser, 1).show();
            MapStrictMode.strictModeViolation(e);
        }
    }

    private static class AttributionBuilder {
        private final WeakReference<Context> context;
        private final MapLibreMap maplibreMap;

        AttributionBuilder(MapLibreMap mapLibreMap, Context context) {
            this.maplibreMap = mapLibreMap;
            this.context = new WeakReference<>(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Set<Attribution> build() {
            Context context = this.context.get();
            if (context == null) {
                return Collections.emptySet();
            }
            ArrayList arrayList = new ArrayList();
            Style style = this.maplibreMap.getStyle();
            if (style != null) {
                Iterator<Source> it = style.getSources().iterator();
                while (it.hasNext()) {
                    String attribution = it.next().getAttribution();
                    if (!attribution.isEmpty()) {
                        arrayList.add(attribution);
                    }
                }
            }
            return new AttributionParser.Options(context).withCopyrightSign(true).withImproveMap(true).withAttributionData((String[]) arrayList.toArray(new String[arrayList.size()])).build().getAttributions();
        }
    }
}
