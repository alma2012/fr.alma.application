/*******************************************************************************
g	 * Copyright (c) 2008, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.alma.application.gen.ui.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.acceleo.module.model2play.cinematic.main.GenerateCinematic;
import org.eclipse.acceleo.module.model2play.entity.main.GenerateEntity;
import org.eclipse.acceleo.module.model2play.soa.main.GenerateSOA;
import org.eclipse.acceleo.module.model2play.soaws.main.GenerateSOAWS;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.dsl.cinematic.CinematicRoot;
import org.obeonetwork.dsl.entity.Block;
import org.obeonetwork.dsl.entity.Root;
import org.obeonetwork.dsl.soa.System;
import org.osgi.framework.Bundle;

import fr.alma.application.Application;


/**
 * Main entry point of the 'Generate Play application' generation module.
 */
public class GenerateAll {

	/**
	 * The model URI.
	 */
	private URI modelURI;

	/**
	 * The output folder.
	 */
	private IContainer targetFolder;

	/**
	 * The other arguments.
	 */
	List<? extends Object> arguments;

	/**
	 * Constructor.
	 * 
	 * @param modelURI
	 *            is the URI of the model.
	 * @param targetFolder
	 *            is the output folder
	 * @param arguments
	 *            are the other arguments
	 * @throws IOException
	 *             Thrown when the output cannot be saved.
	 * @generated
	 */
	public GenerateAll(URI modelURI, IContainer targetFolder, List<? extends Object> arguments) {
		this.modelURI = modelURI;
		this.targetFolder = targetFolder;
		this.arguments = arguments;
	}

	/**
	 * Launches the generation.
	 *
	 * @param monitor
	 *            This will be used to display progress information to the user.
	 * @param enable_webservices 
	 * @throws IOException
	 *             Thrown when the output cannot be saved.
	 * @generated
	 */
	public void doGenerate(IProgressMonitor monitor, boolean enable_webservices) throws IOException {
		if (!targetFolder.getLocation().toFile().exists()) {
			targetFolder.getLocation().toFile().mkdirs();
		}
		
		// final URI template0 = getTemplateURI("fr.alma.application.gen", new Path("/fr/alma/application/gen/main/generate.emtl"));
		// fr.alma.application.gen.main.Generate gen0 = new fr.alma.application.gen.main.Generate(modelURI, targetFolder.getLocation().toFile(), arguments) {
		//	protected URI createTemplateURI(String entry) {
		//		return template0;
		//	}
		//};
		//gen0.doGenerate(BasicMonitor.toMonitor(monitor));
		// TDT ENTITY
		monitor.subTask("Loading...");
		URI entityRootURI = getEntityRootURI(modelURI);
		if (entityRootURI != null) {
		GenerateEntity genEntity = new GenerateEntity(entityRootURI, targetFolder.getLocation().toFile(), arguments);
			monitor.worked(1);
			String generationIDEntity = org.eclipse.acceleo.engine.utils.AcceleoLaunchingUtil.computeUIProjectID("org.eclipse.acceleo.module.model2play.entity", "org.eclipse.acceleo.module.model2play.entity.main.GenerateEntity", modelURI.toString(), targetFolder.getFullPath().toString(), new ArrayList<String>());
			genEntity.setGenerationID(generationIDEntity);
			genEntity.doGenerate(BasicMonitor.toMonitor(monitor));
			monitor.worked(1);
		}
		
		// TDT CINEMATIC
		monitor.subTask("Loading...");
		URI cinematicRootURI = getCinematicRootURI(modelURI);
		if (cinematicRootURI != null) {
			GenerateCinematic genCin = new GenerateCinematic(cinematicRootURI, targetFolder.getLocation().toFile(), arguments);
			monitor.worked(1);
			String generationIDCinematic = org.eclipse.acceleo.engine.utils.AcceleoLaunchingUtil.computeUIProjectID("org.eclipse.acceleo.module.model2play.cinematic", "org.eclipse.acceleo.module.model2play.cinematic.main.GenerateCinematic", modelURI.toString(), targetFolder.getFullPath().toString(), new ArrayList<String>());
			genCin.setGenerationID(generationIDCinematic);
			genCin.doGenerate(BasicMonitor.toMonitor(monitor));
			monitor.worked(1);
		}
		
		// TDT SOA
		monitor.subTask("Loading...");
		URI soaRootURI = getSoaRootURI(modelURI);
		if (soaRootURI != null) {
			GenerateSOA genSoa = new GenerateSOA(soaRootURI, targetFolder.getLocation().toFile(), arguments);
			monitor.worked(1);
			String generationIDSoa = org.eclipse.acceleo.engine.utils.AcceleoLaunchingUtil.computeUIProjectID("org.eclipse.acceleo.module.model2play.soa", "org.eclipse.acceleo.module.model2play.soa.main.GenerateSOA", modelURI.toString(), targetFolder.getFullPath().toString(), new ArrayList<String>());
			genSoa.setGenerationID(generationIDSoa);
			genSoa.doGenerate(BasicMonitor.toMonitor(monitor));
			monitor.worked(1);
		}
		
		if ( enable_webservices ) {
			
			// TDT SOAWS
			monitor.subTask("Loading...");
			if (soaRootURI != null) {
				GenerateSOAWS genSoaWS = new GenerateSOAWS(soaRootURI, targetFolder.getLocation().toFile(), arguments);
				monitor.worked(1);
				String generationIDSoaWS = org.eclipse.acceleo.engine.utils.AcceleoLaunchingUtil.computeUIProjectID("org.eclipse.acceleo.module.model2play.soaws", "org.eclipse.acceleo.module.model2play.soaws.main.GenerateSOAWS", modelURI.toString(), targetFolder.getFullPath().toString(), new ArrayList<String>());
				genSoaWS.setGenerationID(generationIDSoaWS);
				genSoaWS.doGenerate(BasicMonitor.toMonitor(monitor));
				monitor.worked(1);
			}
			
		}
		
	}
	
	
	
	private URI getEntityRootURI(URI applicationModelURI) {
		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.getResource(modelURI, true);
		EObject object = resource.getContents().get(0);
		if (object instanceof Application) {
			Application application = (Application) object;
			for (EObject model : application.getModels()) {
				if (model instanceof Block) {
					return EcoreUtil.getURI(model);
				}
			}
		}
		
		return null;
	}
	private URI getCinematicRootURI(URI applicationModelURI) {
		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.getResource(modelURI, true);
		EObject object = resource.getContents().get(0);
		if (object instanceof Application) {
			Application application = (Application) object;
			for (EObject model : application.getModels()) {
				if (model instanceof CinematicRoot) {
					return EcoreUtil.getURI(model);
				}
			}
		}
		
		return null;
	}
	
	private URI getSoaRootURI(URI applicationModelURI) {
		URI result = null;
		
		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.getResource(modelURI, true);
		EObject object = resource.getContents().get(0);
		if (object instanceof Application) {
			Application application = (Application) object;
			application.getModels();
			for (EObject model : application.getModels()) {
				if (model instanceof System) {
					return EcoreUtil.getURI(model);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Finds the template in the plug-in. Returns the template plug-in URI.
	 * 
	 * @param bundleID
	 *            is the plug-in ID
	 * @param relativePath
	 *            is the relative path of the template in the plug-in
	 * @return the template URI
	 * @throws IOException
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	private URI getTemplateURI(String bundleID, IPath relativePath) throws IOException {
		Bundle bundle = Platform.getBundle(bundleID);
		if (bundle == null) {
			// no need to go any further
			return URI.createPlatformResourceURI(new Path(bundleID).append(relativePath).toString(), false);
		}
		URL url = bundle.getEntry(relativePath.toString());
		if (url == null && relativePath.segmentCount() > 1) {
			Enumeration<URL> entries = bundle.findEntries("/", "*.emtl", true);
			if (entries != null) {
				String[] segmentsRelativePath = relativePath.segments();
				while (url == null && entries.hasMoreElements()) {
					URL entry = entries.nextElement();
					IPath path = new Path(entry.getPath());
					if (path.segmentCount() > relativePath.segmentCount()) {
						path = path.removeFirstSegments(path.segmentCount() - relativePath.segmentCount());
					}
					String[] segmentsPath = path.segments();
					boolean equals = segmentsPath.length == segmentsRelativePath.length;
					for (int i = 0; equals && i < segmentsPath.length; i++) {
						equals = segmentsPath[i].equals(segmentsRelativePath[i]);
					}
					if (equals) {
						url = bundle.getEntry(entry.getPath());
					}
				}
			}
		}
		URI result;
		if (url != null) {
			result = URI.createPlatformPluginURI(new Path(bundleID).append(new Path(url.getPath())).toString(), false);
		} else {
			result = URI.createPlatformResourceURI(new Path(bundleID).append(relativePath).toString(), false);
		}
		return result;
	}

}
