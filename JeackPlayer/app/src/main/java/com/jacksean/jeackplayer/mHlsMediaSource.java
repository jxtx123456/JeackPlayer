package com.jacksean.jeackplayer;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.database.ExoDatabaseProvider;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Util;

class mHlsMediaSource {

    public static HlsMediaSource getMediaSource(Context context){
        Uri contentUri = Uri.parse(Constant.ccty1);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getPackageName()));
        CacheDataSourceFactory cacheDataSourceFactory=new CacheDataSourceFactory(new SimpleCache(context.getFilesDir(),new NoOpCacheEvictor(),new ExoDatabaseProvider(context)),dataSourceFactory);
        com.google.android.exoplayer2.source.hls.HlsMediaSource.Factory factory=new com.google.android.exoplayer2.source.hls.HlsMediaSource.Factory(cacheDataSourceFactory);
        return factory.createMediaSource(contentUri);
    }

}
