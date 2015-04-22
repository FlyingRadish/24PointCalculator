package org.houxg.monkeyhey.util.logger;

import com.avos.avoscloud.AVObject;

/**
 * Created by houxg on 2015/4/13.
 */
public class NetLogger implements LogNode {

    private final static int OUTPUT_PRIORITY = Log.ERROR;

    @Override
    public void log(int priority, String tag, String content) {
        if (priority < OUTPUT_PRIORITY) {
            return;
        }
        AVObject object = new AVObject("BugReport");
        object.put("priority", Log.getPriorityStr(priority));
        object.put("tag", tag);
        object.put("content", content);
        object.saveInBackground();
    }
}
