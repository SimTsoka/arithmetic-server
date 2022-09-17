package Server;

import org.json.JSONObject;

public class JSONParser {
    private String name;
    private String msg;
    private String sumType;
    private String sumValue;
    private String status;
    private JSONObject reply = new JSONObject();

    public JSONObject errorMessage(String msg) {
        reply.put("status", "ERROR");
        reply.put("message", msg);
        reply.put("sumType", "");
        reply.put("sumValue","");

        return reply;
    }

    public JSONObject errorMessage(String name, String msg) {
        reply = errorMessage(msg);
        reply.put("name", name);

        return reply;
    }
}
