package novel.mzx.com.event;

/**
 * Created by admin on 2019/11/20.
 */

public class MessageEvent {
    private String message;
    private String type;
    public  MessageEvent(String message){
        this.message=message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
