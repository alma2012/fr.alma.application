/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.alma.application;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.obeonetwork.dsl.environment.ObeoDSMObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.alma.application.Application#getModels <em>Models</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.alma.application.ApplicationPackage#getApplication()
 * @model
 * @generated
 */
public interface Application extends EObject {
	/**
	 * Returns the value of the '<em><b>Models</b></em>' reference list.
	 * The list contents are of type {@link org.obeonetwork.dsl.environment.ObeoDSMObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Models</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Models</em>' reference list.
	 * @see fr.alma.application.ApplicationPackage#getApplication_Models()
	 * @model
	 * @generated
	 */
	EList<ObeoDSMObject> getModels();

} // Application
