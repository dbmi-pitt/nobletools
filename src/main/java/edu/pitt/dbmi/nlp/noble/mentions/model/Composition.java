package edu.pitt.dbmi.nlp.noble.mentions.model;

import java.util.*;

import edu.pitt.dbmi.nlp.noble.coder.model.Document;
import edu.pitt.dbmi.nlp.noble.ontology.IClass;
import edu.pitt.dbmi.nlp.noble.ontology.IInstance;
import edu.pitt.dbmi.nlp.noble.ontology.IOntology;
import edu.pitt.dbmi.nlp.noble.ontology.IProperty;
import edu.pitt.dbmi.nlp.noble.ontology.OntologyUtils;

public class Composition extends Document {
	protected IClass cls;
	protected IInstance instance;
	private List<AnnotationVariable> annotationVariables,rejectedVariables;
	private DomainOntology domainOntology;
	
	public Composition(String text){
		super(text);
	}
	
	public DomainOntology getDomainOntology() {
		return domainOntology;
	}

	public void setDomainOntology(DomainOntology domainOntology) {
		this.domainOntology = domainOntology;
	}

	/**
	 * get concept class representing this mention
	 * @return
	 */
	
	public IClass getConceptClass() {
		if(cls == null){
			cls = getDomainOntology().getOntology().getClass(DomainOntology.COMPOSITION);
		}
		return cls;
	}
	
	/**
	 * get an instance representing this mention
	 * @return
	 */
	public IInstance getInstance() {
		IOntology ontology = getDomainOntology().getOntology();
		IClass cls = getConceptClass();
		
		
		// what's the point if we have no class?
		if(cls == null)
			return null;
		
		// init instance
		if(instance == null){
			// get the title of the document
			String title = getTitle();
			
			// create an instance from title or class name
			instance = title != null? cls.createInstance(OntologyUtils.toResourceName(title)):cls.createInstance(domainOntology.createInstanceName(cls));
			
			// add title
			if(title != null){
				instance.addPropertyValue(ontology.getProperty(DomainOntology.HAS_TITLE),title);
			}
			
			// add annotation variable
			/*
			IProperty hasEvent = ontology.getProperty(DomainOntology.HAS_MENTION_ANNOTATION);
			for(AnnotationVariable av : getAnnotationVariables()){
				instance.addPropertyValue(hasEvent, av.getInstance());
			}
			*/
		}
		return instance;
	}
	
	
	/**
	 * get a set of annotation variables extracted from a document
	 * @return
	 */
	public List<AnnotationVariable> getAnnotationVariables(){
		if(annotationVariables == null)
			annotationVariables = new ArrayList<AnnotationVariable>();
		return annotationVariables;
	}

	/**
	 * get a set of annotation variables extracted from a document
	 * @return
	 */
	public List<AnnotationVariable> getRejectedAnnotationVariables(){
		if(rejectedVariables == null)
			rejectedVariables = new ArrayList<AnnotationVariable>();
		return rejectedVariables;
	}
	
	/**
	 * add an annotation variable
	 * @param var
	 */
	public void addAnnotationVariable(AnnotationVariable var) {
		getAnnotationVariables().add(var);
		IProperty hasEvent = var.getDomainOntology().getOntology().getProperty(DomainOntology.HAS_MENTION_ANNOTATION);
		getInstance().addPropertyValue(hasEvent,var.getInstance());
	}
}
