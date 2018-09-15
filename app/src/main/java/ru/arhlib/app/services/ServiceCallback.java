package ru.arhlib.app.services;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import ru.arhlib.app.MyWebViewClient;
import ru.arhlib.app.R;

public class ServiceCallback {

    public void onClick(View view, Service service) {
        Context context = view.getContext();
        if (service.name == R.string.catalog) {
            MyWebViewClient.openUrl(context, context.getString(R.string.catalog_url));
        } else if (service.name == R.string.prolongation) {
            MyWebViewClient.openUrl(context, context.getString(R.string.prolongation_url));
        } else if (service.name == R.string.contacts) {
            context.startActivity(new Intent(context, ContactsActivity.class));
        } else if (service.name == R.string.virtual_help) {
            MyWebViewClient.openUrl(context, context.getString(R.string.virtual_help_url));
        } else if (service.name == R.string.ask_lawyer) {
            MyWebViewClient.openUrl(context, context.getString(R.string.lawyer_url));
        } else if (service.name == R.string.about) {
            context.startActivity(new Intent(context, AboutActivity.class));
        }
    }
}
