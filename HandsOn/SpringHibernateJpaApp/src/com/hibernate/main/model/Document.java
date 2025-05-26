package com.hibernate.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false, name = "document_heading")
	private String documentHeading;
	@Column(name = "document_link")
	private String documentLink;
	@ManyToOne
	private Module module;
	
	public Document() { }

	public Document(int id, String documentHeading, String documentLink, Module module) {
		super();
		this.id = id;
		this.documentHeading = documentHeading;
		this.documentLink = documentLink;
		this.module = module;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getDocumentHeading() { return documentHeading; }
	public void setDocumentHeading(String documentHeading) { this.documentHeading = documentHeading; }
	public String getDocumentLink() { return documentLink; }
	public void setDocumentLink(String documentLink) { this.documentLink = documentLink; }
	public Module getModule() { return module; }
	public void setModule(Module module) { this.module = module; }
	
	@Override
	public String toString() {
		return "Document [id=" + id + ", documentHeading=" + documentHeading + ", documentLink=" + documentLink
				+ ", module=" + module + "]";
	}
	
}
