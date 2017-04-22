/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.lsuc.lsuc.Businessperson;
import com.lsuc.lsuc.Licensee;
import com.lsuc.lsuc.Licenseephotoidcard;
import com.lsuc.lsuc.Mailinglabel;
import com.lsuc.lsuc.Person;
import com.lsuc.lsuc.Personaddress;
import com.lsuc.lsuc.Personemailcontact;
import com.lsuc.lsuc.Personlanguage;
import com.lsuc.lsuc.Personnameotherlanguage;
import com.lsuc.lsuc.Personothername;
import com.lsuc.lsuc.Personperson;
import com.lsuc.lsuc.Personphonecontact;
import com.lsuc.lsuc.Personrole;
import com.lsuc.lsuc.Personsocialmediacontact;
import com.lsuc.lsuc.User;


/**
 * ServiceImpl object for domain model class Person.
 *
 * @see Person
 */
@Service("LSUC.PersonService")
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.PersonpersonService")
	private PersonpersonService personpersonService;

    @Autowired
	@Qualifier("LSUC.PersonothernameService")
	private PersonothernameService personothernameService;

    @Autowired
	@Qualifier("LSUC.PersonlanguageService")
	private PersonlanguageService personlanguageService;

    @Autowired
	@Qualifier("LSUC.PersonsocialmediacontactService")
	private PersonsocialmediacontactService personsocialmediacontactService;

    @Autowired
	@Qualifier("LSUC.PersonnameotherlanguageService")
	private PersonnameotherlanguageService personnameotherlanguageService;

    @Autowired
	@Qualifier("LSUC.UserService")
	private UserService userService;

    @Autowired
	@Qualifier("LSUC.LicenseeService")
	private LicenseeService licenseeService;

    @Autowired
	@Qualifier("LSUC.PersonaddressService")
	private PersonaddressService personaddressService;

    @Autowired
	@Qualifier("LSUC.PersonphonecontactService")
	private PersonphonecontactService personphonecontactService;

    @Autowired
	@Qualifier("LSUC.BusinesspersonService")
	private BusinesspersonService businesspersonService;

    @Autowired
	@Qualifier("LSUC.LicenseephotoidcardService")
	private LicenseephotoidcardService licenseephotoidcardService;

    @Autowired
	@Qualifier("LSUC.PersonemailcontactService")
	private PersonemailcontactService personemailcontactService;

    @Autowired
	@Qualifier("LSUC.MailinglabelService")
	private MailinglabelService mailinglabelService;

    @Autowired
	@Qualifier("LSUC.PersonroleService")
	private PersonroleService personroleService;

    @Autowired
    @Qualifier("LSUC.PersonDao")
    private WMGenericDao<Person, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Person, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Person create(Person person) {
        LOGGER.debug("Creating a new Person with information: {}", person);
        Person personCreated = this.wmGenericDao.create(person);
        if(personCreated.getLicenseephotoidcardsForPersonFkVerified() != null) {
            for(Licenseephotoidcard licenseephotoidcardsForPersonFkVerified : personCreated.getLicenseephotoidcardsForPersonFkVerified()) {
                licenseephotoidcardsForPersonFkVerified.setPersonByPersonFkVerified(personCreated);
                LOGGER.debug("Creating a new child Licenseephotoidcard with information: {}", licenseephotoidcardsForPersonFkVerified);
                licenseephotoidcardService.create(licenseephotoidcardsForPersonFkVerified);
            }
        }

        if(personCreated.getLicenseephotoidcardsForPersonFkIssued() != null) {
            for(Licenseephotoidcard licenseephotoidcardsForPersonFkIssued : personCreated.getLicenseephotoidcardsForPersonFkIssued()) {
                licenseephotoidcardsForPersonFkIssued.setPersonByPersonFkIssued(personCreated);
                LOGGER.debug("Creating a new child Licenseephotoidcard with information: {}", licenseephotoidcardsForPersonFkIssued);
                licenseephotoidcardService.create(licenseephotoidcardsForPersonFkIssued);
            }
        }

        if(personCreated.getPersonemailcontacts() != null) {
            for(Personemailcontact personemailcontact : personCreated.getPersonemailcontacts()) {
                personemailcontact.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personemailcontact with information: {}", personemailcontact);
                personemailcontactService.create(personemailcontact);
            }
        }

        if(personCreated.getMailinglabel() != null) {
            Mailinglabel mailinglabel = personCreated.getMailinglabel();
            LOGGER.debug("Creating a new child Mailinglabel with information: {}", mailinglabel);
            mailinglabel.setPerson(personCreated);
            mailinglabelService.create(mailinglabel);
        }

        if(personCreated.getPersonphonecontacts() != null) {
            for(Personphonecontact personphonecontact : personCreated.getPersonphonecontacts()) {
                personphonecontact.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personphonecontact with information: {}", personphonecontact);
                personphonecontactService.create(personphonecontact);
            }
        }

        if(personCreated.getPersonsocialmediacontacts() != null) {
            for(Personsocialmediacontact personsocialmediacontact : personCreated.getPersonsocialmediacontacts()) {
                personsocialmediacontact.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personsocialmediacontact with information: {}", personsocialmediacontact);
                personsocialmediacontactService.create(personsocialmediacontact);
            }
        }

        if(personCreated.getPersonpersonsForPersonFkParent() != null) {
            for(Personperson personpersonsForPersonFkParent : personCreated.getPersonpersonsForPersonFkParent()) {
                personpersonsForPersonFkParent.setPersonByPersonFkParent(personCreated);
                LOGGER.debug("Creating a new child Personperson with information: {}", personpersonsForPersonFkParent);
                personpersonService.create(personpersonsForPersonFkParent);
            }
        }

        if(personCreated.getPersonpersonsForPersonFkChild() != null) {
            for(Personperson personpersonsForPersonFkChild : personCreated.getPersonpersonsForPersonFkChild()) {
                personpersonsForPersonFkChild.setPersonByPersonFkChild(personCreated);
                LOGGER.debug("Creating a new child Personperson with information: {}", personpersonsForPersonFkChild);
                personpersonService.create(personpersonsForPersonFkChild);
            }
        }

        if(personCreated.getLicensees() != null) {
            for(Licensee licensee : personCreated.getLicensees()) {
                licensee.setPerson(personCreated);
                LOGGER.debug("Creating a new child Licensee with information: {}", licensee);
                licenseeService.create(licensee);
            }
        }

        if(personCreated.getUsers() != null) {
            for(User user : personCreated.getUsers()) {
                user.setPerson(personCreated);
                LOGGER.debug("Creating a new child User with information: {}", user);
                userService.create(user);
            }
        }

        if(personCreated.getBusinesspersons() != null) {
            for(Businessperson businessperson : personCreated.getBusinesspersons()) {
                businessperson.setPerson(personCreated);
                LOGGER.debug("Creating a new child Businessperson with information: {}", businessperson);
                businesspersonService.create(businessperson);
            }
        }

        if(personCreated.getPersonothernames() != null) {
            for(Personothername personothername : personCreated.getPersonothernames()) {
                personothername.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personothername with information: {}", personothername);
                personothernameService.create(personothername);
            }
        }

        if(personCreated.getPersonnameotherlanguages() != null) {
            for(Personnameotherlanguage personnameotherlanguage : personCreated.getPersonnameotherlanguages()) {
                personnameotherlanguage.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personnameotherlanguage with information: {}", personnameotherlanguage);
                personnameotherlanguageService.create(personnameotherlanguage);
            }
        }

        if(personCreated.getPersonroles() != null) {
            for(Personrole personrole : personCreated.getPersonroles()) {
                personrole.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personrole with information: {}", personrole);
                personroleService.create(personrole);
            }
        }

        if(personCreated.getPersonlanguages() != null) {
            for(Personlanguage personlanguage : personCreated.getPersonlanguages()) {
                personlanguage.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personlanguage with information: {}", personlanguage);
                personlanguageService.create(personlanguage);
            }
        }

        if(personCreated.getPersonaddresses() != null) {
            for(Personaddress personaddresse : personCreated.getPersonaddresses()) {
                personaddresse.setPerson(personCreated);
                LOGGER.debug("Creating a new child Personaddress with information: {}", personaddresse);
                personaddressService.create(personaddresse);
            }
        }
        return personCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Person getById(Integer personId) throws EntityNotFoundException {
        LOGGER.debug("Finding Person by id: {}", personId);
        Person person = this.wmGenericDao.findById(personId);
        if (person == null){
            LOGGER.debug("No Person found with id: {}", personId);
            throw new EntityNotFoundException(String.valueOf(personId));
        }
        return person;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Person findById(Integer personId) {
        LOGGER.debug("Finding Person by id: {}", personId);
        return this.wmGenericDao.findById(personId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Person update(Person person) throws EntityNotFoundException {
        LOGGER.debug("Updating Person with information: {}", person);
        this.wmGenericDao.update(person);

        Integer personId = person.getPk();

        return this.wmGenericDao.findById(personId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Person delete(Integer personId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Person with id: {}", personId);
        Person deleted = this.wmGenericDao.findById(personId);
        if (deleted == null) {
            LOGGER.debug("No Person found with id: {}", personId);
            throw new EntityNotFoundException(String.valueOf(personId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Person> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Persons");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Person> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Persons");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Person to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Licenseephotoidcard> findAssociatedLicenseephotoidcardsForPersonFkVerified(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licenseephotoidcardsForPersonFkVerified");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("personByPersonFkVerified.pk = '" + pk + "'");

        return licenseephotoidcardService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Licenseephotoidcard> findAssociatedLicenseephotoidcardsForPersonFkIssued(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licenseephotoidcardsForPersonFkIssued");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("personByPersonFkIssued.pk = '" + pk + "'");

        return licenseephotoidcardService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personemailcontact> findAssociatedPersonemailcontacts(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personemailcontacts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personemailcontactService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personphonecontact> findAssociatedPersonphonecontacts(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personphonecontacts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personphonecontactService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personsocialmediacontact> findAssociatedPersonsocialmediacontacts(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personsocialmediacontacts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personsocialmediacontactService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personperson> findAssociatedPersonpersonsForPersonFkParent(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personpersonsForPersonFkParent");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("personByPersonFkParent.pk = '" + pk + "'");

        return personpersonService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personperson> findAssociatedPersonpersonsForPersonFkChild(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personpersonsForPersonFkChild");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("personByPersonFkChild.pk = '" + pk + "'");

        return personpersonService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Licensee> findAssociatedLicensees(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licensees");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return licenseeService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<User> findAssociatedUsers(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated users");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return userService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businessperson> findAssociatedBusinesspersons(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated businesspersons");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return businesspersonService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personothername> findAssociatedPersonothernames(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personothernames");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personothernameService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personnameotherlanguage> findAssociatedPersonnameotherlanguages(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personnameotherlanguages");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personnameotherlanguageService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personrole> findAssociatedPersonroles(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personroles");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personroleService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personlanguage> findAssociatedPersonlanguages(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personlanguages");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personlanguageService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personaddress> findAssociatedPersonaddresses(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personaddresses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("person.pk = '" + pk + "'");

        return personaddressService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonpersonService instance
	 */
	protected void setPersonpersonService(PersonpersonService service) {
        this.personpersonService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonothernameService instance
	 */
	protected void setPersonothernameService(PersonothernameService service) {
        this.personothernameService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonlanguageService instance
	 */
	protected void setPersonlanguageService(PersonlanguageService service) {
        this.personlanguageService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonsocialmediacontactService instance
	 */
	protected void setPersonsocialmediacontactService(PersonsocialmediacontactService service) {
        this.personsocialmediacontactService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonnameotherlanguageService instance
	 */
	protected void setPersonnameotherlanguageService(PersonnameotherlanguageService service) {
        this.personnameotherlanguageService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserService instance
	 */
	protected void setUserService(UserService service) {
        this.userService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseeService instance
	 */
	protected void setLicenseeService(LicenseeService service) {
        this.licenseeService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonaddressService instance
	 */
	protected void setPersonaddressService(PersonaddressService service) {
        this.personaddressService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonphonecontactService instance
	 */
	protected void setPersonphonecontactService(PersonphonecontactService service) {
        this.personphonecontactService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinesspersonService instance
	 */
	protected void setBusinesspersonService(BusinesspersonService service) {
        this.businesspersonService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseephotoidcardService instance
	 */
	protected void setLicenseephotoidcardService(LicenseephotoidcardService service) {
        this.licenseephotoidcardService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonemailcontactService instance
	 */
	protected void setPersonemailcontactService(PersonemailcontactService service) {
        this.personemailcontactService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MailinglabelService instance
	 */
	protected void setMailinglabelService(MailinglabelService service) {
        this.mailinglabelService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonroleService instance
	 */
	protected void setPersonroleService(PersonroleService service) {
        this.personroleService = service;
    }

}
