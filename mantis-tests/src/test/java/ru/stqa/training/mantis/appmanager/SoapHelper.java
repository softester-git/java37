package ru.stqa.training.mantis.appmanager;

import ru.stqa.training.mantis.model.Issue;
import ru.stqa.training.mantis.model.Project;

import biz.futureware.mantis.rpc.soap.client.*;
import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private ApplicationManager app;
  private String login;
  private String password;
  private String urlConnect;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
    this.login = app.getProperty("soap.login");
    this.password = app.getProperty("soap.password");
    this.urlConnect = app.getProperty("soap.url");
  }

  public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(login, password);
    return Arrays.asList(projects).stream()
            .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    String[] categories = mc.mc_project_get_categories(login, password, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setCategory(categories[0]);
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    BigInteger issueId = mc.mc_issue_add(login, password, issueData);
    IssueData createdIssueData = mc.mc_issue_get(login, password, issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary())
            .withDescription(createdIssueData.getDescription())
            .withProject(new Project()
                    .withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }

  public String getIssueStatus(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    return mc.mc_issue_get(login, password, BigInteger.valueOf(issueId)).getResolution().getName();
  }


  private MantisConnectPortType getMantisConnect() throws MalformedURLException, ServiceException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(urlConnect));
  }
}
