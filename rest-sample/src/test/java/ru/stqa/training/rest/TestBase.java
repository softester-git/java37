package ru.stqa.training.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Set;

import org.apache.http.client.fluent.Executor;
import org.testng.SkipException;

public class TestBase {
  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private boolean isIssueOpen(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issuesObject = parsed.getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(issuesObject, new TypeToken<Set<Issue>>() {
    }.getType());
    Issue issue = issues.iterator().next();
    if (issue.getState_name().equals("Closed")) {
      return false;
    }
    return true;
  }

  org.apache.http.client.fluent.Executor getExecutor() {
    return Executor.newInstance().auth("b31e382ca8445202e66b03aaf31508a3", "");
  }
}
