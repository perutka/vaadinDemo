package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class TodoUi extends UI{
	
	private VerticalLayout root;
	
	@Autowired
	TodoLayout todoLayout;

	@Override
	protected void init(VaadinRequest request) {
		setupLayout();
		addHeader();
		addForm();
		addTodoList();
		addDeleteButton();
		
	}
	
	private void setupLayout() {
		root = new VerticalLayout();
		setContent(root);
		
	}
	
	private void addHeader() {
		root.addComponent(new Label("TODOs"));
		
	}
	
	private void addForm() {
		HorizontalLayout formLayout = new HorizontalLayout();
		
		TextField task = new TextField();
		Button add = new Button("Add");
		
		
		formLayout.addComponents(task, add);
		
		add.addClickListener(click -> {
			todoLayout.add(new Todo(task.getValue()));
			task.clear();
			task.focus();
		});
		task.focus();
		// Sets enter key to also click the button
		add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
		root.addComponent(formLayout);
	}
	
	private void addTodoList() {
		root.addComponent(todoLayout);
		
	}

	private void addDeleteButton() {
		root.addComponent(new Button("Delete completed", clicl -> {
			todoLayout.deleteCompleted();
		}));
		
	}







	
	

}
