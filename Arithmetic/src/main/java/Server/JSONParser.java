package Server;

import org.json.JSONObject;

public class JSONParser {
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

    public JSONObject successfulMessage(String msg, String name) {
        reply.put("status", "OK");
        reply.put("message", msg);
        reply.put("sumType", "");
        reply.put("sumValue","");
        reply.put("name", name);

        return reply;
    }
}
