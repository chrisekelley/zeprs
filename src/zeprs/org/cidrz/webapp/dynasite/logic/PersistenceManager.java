/*
 *    Copyright 2003, 2004, 2005, 2006 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.cidrz.webapp.dynasite.logic;

import org.cidrz.webapp.dynasite.valueobject.*;
import org.cidrz.webapp.dynasite.valueobject.Patient;
import org.cidrz.webapp.dynasite.exception.ObjectNotFoundException;
import org.cidrz.webapp.dynasite.exception.PersistenceException;
import org.cidrz.webapp.dynasite.rules.Outcome;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.hibernate.NonUniqueResultException;

/**
 * Interface for all persistence operations
 */
public interface PersistenceManager {
    /**
     * Obtains all instances of the class this PersistenceManager handles,
     * as determined when the implementation of this PersistenceManager
     * was obtained from the PersistenceManagerFactory.
     *
     * @return A List of instances of the managed class.
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getAll() throws PersistenceException;

    /**
     * Obtains all instances of the class this PersistenceManager handles,
     * in the specified order
     *
     * @param orderName
     * @return A List of instances of the managed class.
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getAll(String orderName) throws PersistenceException;

    /**
     * Gets all instances with the matching patient. Provide name of patient id in the class.
     *
     * @param patient
     * @param patientIdName
     * @param currentPregnancyId
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public List getAllForPatient(String patientIdName, Patient patient, Long currentPregnancyId) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single instance with the matching id.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */

    public Identifiable getOne(Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single instance with the matching id. Fresh copy.
     *
     * @param id
     * @return The matching instance
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getFreshOne(Long id) throws PersistenceException, ObjectNotFoundException, IOException;

    /**
     * Gets a single instance with the matching id.
     *
     * @param example
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getOne(Identifiable example) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets all instances with this parent id. Only useful for classes that have recursive relationships.
     *
     * @param parentId
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getChildren(Long parentId) throws PersistenceException;

    /**
     * Gets all instances with this parent id, ordered. Only useful for classes that have recursive relationships.
     *
     * @param parentId
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getChildren(Long parentId, String orderName) throws PersistenceException;

    /**
     * Persists the object
     *
     * @param persistentObject
     * @param site
     * @return The saved object in it's post-save state.
     * @throws org.cidrz.webapp.dynasite.exception.PersistenceException If an error occurs in the persistence layer
     */
    public Identifiable save(Identifiable persistentObject, Principal user, Site site) throws PersistenceException;

    /**
     * Deletes the object
     *
     * @param persistentObject
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public void delete(Identifiable persistentObject, Principal user) throws PersistenceException;

    /**
     * Searches for these tracking fields
     *
     * @param patient
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getSharedfields(Patient patient) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single instance of Patient with the matching id.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getPatient(Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single instance of an Encounter with the matching id.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getEncounter(Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets problems from patient with the matching id.
     *
     * @param patient
     * @param pregnancyId
     * @return List of problems
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public List getProblems(Patient patient, Boolean status, Long pregnancyId) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets comments from patient/problemwith the matching id.
     *
     * @param patientId
     * @param problem
     * @return List of comments
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public List getComments(Long patientId, Outcome problem) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets comments from patient/outcome with the matching id.
     *
     * @param patientId
     * @param outcome
     * @return List of comments
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public List getComments(Long patientId, Problem outcome) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets most recent comments for patient, depending upon the status
     *
     * @param patientId
     * @param status
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getMostrecentcomments(Long patientId, Boolean status) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets most recent comments for patient, depending upon the status
     *
     * @param patientId
     * @param status
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getMostrecentOutcomecomments(Long patientId, Boolean status) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single problem with the matching id. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getProblem(Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single outcome with the matching id. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getOutcome(Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a list of all outcomes and problems for this patient
     *
     * @param patient
     * @param status
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getAlerts(Patient patient, Boolean status) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets the outcomes with the matching patient id.
     *
     * @param patientId
     * @param status
     * @param pregnancyId
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public List getOutcomes(Long patientId, Boolean status, Long pregnancyId) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets the outcomes with the matching patient id and encounter id.
     *
     * @param patientId
     * @param status
     * @param pregnancyId
     * @param encounterId
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public List getOutcomes(Long patientId, Boolean status, Long pregnancyId, Long encounterId) throws PersistenceException, ObjectNotFoundException;

    /**
     * Get all fields for populating the formbean. Cannot use the standard getAll because some of the pageitems are display-only
     * and should not be put into formbean.
     *
     * @return A List of instances of the managed class.
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    /* public List getAllFormFields() throws PersistenceException;*/

    /**
     * Find if user has filled out this particular form
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getFormFromEncounter(Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Return list of antenatal record exam.
     *
     * @param patientId
     * @param pregnancyId
     * @return
     * @throws PersistenceException
     * @throws ObjectNotFoundException
     */
    public List getPatientRecords(Long patientId, Long pregnancyId) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets all enabled forms. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @return The matching instance
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getAllForms() throws PersistenceException;

    /**
     * Gets a single instance with the matching id. Fetch the whole mutha(FetchMode.EAGER).
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getOneForm(Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets all instances with this fieldId.
     * <p/>
     * todo: this class could use the mappings or reflection to figure out the parent column.
     *
     * @param fieldId
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getEnumerations(Long fieldId) throws PersistenceException;

    /**
     * get list of all shared FormFields
     *
     * @return
     * @throws PersistenceException
     */
    public List getAllSharedfields() throws PersistenceException;

    /**
     * Gets all instances with this ipaddress.
     *
     * @param ipAddress
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getSettings(String ipAddress) throws PersistenceException;

    /**
     * Find if we've got a duplicate patient id
     *
     * @param artPatientid
     * @return
     * @throws PersistenceException
     * @throws ObjectNotFoundException
     */
    public org.cidrz.webapp.dynasite.valueobject.Identifiable getPatientId(String artPatientid) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single flow with the matching flowOrder.
     *
     * @param flowOrder
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getFlowByOrder(Long flowOrder) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets all instances of patients with this parent id.
     *
     * @param parent
     * @return A List of matching instances
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public List getChildren(Patient parent) throws PersistenceException;

    /**
     * Obtains all reports for form
     *
     * @param form
     * @return
     * @throws PersistenceException
     */
    public List getReports(Form form) throws PersistenceException;

    /**
     * Obtains all reports for a form_field. Removes duplicates
     *
     * @param form_field
     * @return
     * @throws PersistenceException
     */
    public List getReports(FormField form_field) throws PersistenceException;

    /**
     * Obtains all reports that have shared fields
     *
     * @return
     * @throws PersistenceException
     */
    public List getSharedReports() throws PersistenceException;

    /**
     * Gets a single instance with the matching id.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public User getUser(String id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Fetches the regimen, sorted by name. Feed it the regimentype.
     *
     * @param regimenType
     * @return list of regimens
     */
    public List getRegimen(Long regimenType) throws PersistenceException;

    /**
     * Persists the object - used by remote
     *
     * @param persistentObject
     * @param user
     * @param site
     * @return
     * @throws PersistenceException
     */

    public Identifiable save(Identifiable persistentObject, String user, Site site) throws PersistenceException;

    /**
     * Gets a single instance with the matching id from userdata db.
     *
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */

    public Staff getStaff(String id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets all instances of this object this patient id and pregnancy
     *
     * @param patientId
     * @return the object
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public Identifiable getPatientData(Long patientId, Long pregnancyId) throws PersistenceException, ObjectNotFoundException, NonUniqueResultException;

    /**
     * Gets most recent record for this patient id and pregnancy
     *
     * @param patientId
     * @return the object
     * @throws PersistenceException If an error occurs in the persistence layer
     */
    public Identifiable getMostRecent(Long patientId, Long pregnancyId) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single instance with the matching property.
     *
     * @param property
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */

    public Identifiable getOnebyProperty(String property, Long id) throws PersistenceException, ObjectNotFoundException;

    /**
     * Gets a single instance with the matching property.
     *
     * @param property
     * @param id
     * @return The matching instance
     * @throws PersistenceException    If an error occurs in the persistence layer
     * @throws ObjectNotFoundException If no match was found.
     */
    public Identifiable getOnebyProperty(String property, Integer id) throws PersistenceException, ObjectNotFoundException;
}
