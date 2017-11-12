package eu.b24u.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window (or tab) or
 * some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("mytheme")
@Viewport("initial-scale=1, maximum-scale=1")
@SuppressWarnings("serial")
public class MyUI extends UI {

  @WebServlet(value = "/*", asyncSupported = true)
  public static class Servlet extends SpringVaadinServlet {
  }

  @WebListener
  public static class MyContextLoaderListener extends ContextLoaderListener {
  }

  @Configuration
  @EnableVaadin
  public static class MyConfiguration {
  }
  
  @Autowired
  SpringViewProvider viewProvider;

  @Override
  protected void init(VaadinRequest request) {
    Panel loginPanel = new Panel("Login");
    CustomLayout content = new CustomLayout("my-layout");
    content.setSizeUndefined();
    loginPanel.setContent(content);
    loginPanel.setSizeUndefined();

    // No captions for fields is they are provided in the template
    TextField username = new TextField();
    content.addComponent(username, "username");
    PasswordField password = new PasswordField();
    content.addComponent(password, "password");
    Button loginButton = new Button(); 
    content.addComponent(loginButton, "okbutton");
    loginButton.addClickListener(e->{
    	if(username.getValue().equals("admin")&&password.getValue().equals("secret")) {
    		Notification.show("OK");
    		FormLayout secretLayout = new FormLayout();
    		secretLayout.addComponent(new Label("My secret page"));
    		setContent(secretLayout);
    	}else {
    		Notification.show("ERROR");
    	}
    });
    loginButton.setCaption("Login");
    
    setContent(content);
  }
}
