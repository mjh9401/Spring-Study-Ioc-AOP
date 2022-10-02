package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View {
    void render(Map<String,?>Model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
