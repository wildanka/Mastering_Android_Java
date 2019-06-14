package com.wildanka.stackwidget

import android.content.Intent
import android.widget.RemoteViewsService
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory

//register the service in manifest
class StackWidgetService: RemoteViewsService(){
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return StackRemoteViewsFactory(this.applicationContext)
    }

}
