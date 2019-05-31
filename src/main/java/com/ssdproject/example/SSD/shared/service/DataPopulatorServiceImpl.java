package com.ssdproject.example.SSD.shared.service;

import com.ssdproject.example.SSD.auth.dao.*;
import com.ssdproject.example.SSD.auth.model.entity.RoleEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.OrganiserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.ReviewerEntity;
import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import com.ssdproject.example.SSD.auth.model.enums.UserRoleName;
import com.ssdproject.example.SSD.conference.dao.AddressDao;
import com.ssdproject.example.SSD.conference.dao.ConferenceDao;
import com.ssdproject.example.SSD.conference.dao.ConferenceInformationDao;
import com.ssdproject.example.SSD.conference.model.entity.AddressEntity;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceInformationEntity;
import com.ssdproject.example.SSD.conference.model.enums.Country;
import com.ssdproject.example.SSD.payment.dao.CurrencyValueDao;
import com.ssdproject.example.SSD.payment.model.entity.CurrencyValueEntity;
import com.ssdproject.example.SSD.payment.model.enums.Currency;
import com.ssdproject.example.SSD.payment.model.enums.ValueType;
import com.ssdproject.example.SSD.review.dao.RemarkDao;
import com.ssdproject.example.SSD.review.dao.ReviewDao;
import com.ssdproject.example.SSD.review.model.entity.RemarkEntity;
import com.ssdproject.example.SSD.review.model.entity.ReviewEntity;
import com.ssdproject.example.SSD.review.model.enums.ReviewStatus;
import com.ssdproject.example.SSD.schedule.dao.PosterDao;
import com.ssdproject.example.SSD.schedule.dao.PresentationDao;
import com.ssdproject.example.SSD.schedule.dao.ScheduleDao;
import com.ssdproject.example.SSD.schedule.dao.ScheduleItemDao;
import com.ssdproject.example.SSD.schedule.model.entity.PosterEntity;
import com.ssdproject.example.SSD.schedule.model.entity.PresentationEntity;
import com.ssdproject.example.SSD.schedule.model.entity.ScheduleEntity;
import com.ssdproject.example.SSD.schedule.model.entity.ScheduleItemEntity;
import com.ssdproject.example.SSD.schedule.model.enums.ScheduleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataPopulatorServiceImpl {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CurrencyValueDao currencyValueDao;
    @Autowired
    private RemarkDao remarkDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private ConferenceInformationDao conferenceInformationDao;
    @Autowired
    private ConferenceDao conferenceDao;
    @Autowired
    private ScheduleItemDao scheduleItemDao;
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private PresentationDao presentationDao;
    @Autowired
    private PosterDao posterDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private OrganiserDao organiserDao;
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private ReviewerDao reviewerDao;

    private RoleEntity roleOrganiser;
    private RoleEntity roleGuest;
    private RoleEntity roleReviewer;
    private RoleEntity roleAuthor;

    private CurrencyValueEntity pricePlnStudent;
    private CurrencyValueEntity pricePlnStandard;
    private CurrencyValueEntity pricePLNVip;

    public void populateDataBase() {
        saveRoles();
        saveCurrency();
    }

    public ConferenceEntity buildConference() {
        return null;
    }

    public RemarkEntity saveRemark(String remark) {
        return remarkDao.save(buildRemark(remark));
    }

    public ReviewEntity saveReview(ReviewerEntity reviewer, PosterEntity poster, PresentationEntity presentation,
                                   ReviewStatus status, LocalDateTime reviewDueDate, LocalDateTime reviewFinishDate, List<RemarkEntity> remarks) {
        return reviewDao.save(buildReview(reviewer, poster, presentation, status, reviewDueDate, reviewFinishDate, remarks));
    }

    public ConferenceInformationEntity saveConferenceInformation(String topic, String description, int maxNumberOfPresentations, int maxNumberOfSeats) {
        return conferenceInformationDao.save(buildConferenceInformation(topic, description, maxNumberOfPresentations, maxNumberOfSeats));
    }

    public ConferenceEntity saveConference(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime registrationDate,
                                           ConferenceInformationEntity conferenceInformation,
                                           List<OrganiserEntity> organisers, List<AuthorEntity> authors,
                                           List<GuestEntity> guests, ScheduleEntity schedule, AddressEntity address) {
        return conferenceDao.save(buildConference(startDate, endDate, registrationDate, conferenceInformation, organisers, authors, guests, schedule, address));
    }

    public ScheduleItemEntity saveScheduleItem(String topic, String description, String localizationDetail,
                                               LocalDateTime startDate, LocalDateTime endDate,
                                               List<PosterEntity> posters, PresentationEntity presentation) {
        return scheduleItemDao.save(buildScheduleItem(topic, description, localizationDetail, startDate, endDate, posters, presentation));
    }

    public PresentationEntity savePresentation(String title, String url, LocalDateTime fileAttachingDate, List<AuthorEntity> authors) {
        return presentationDao.save(buildPresentation(title, url, fileAttachingDate, authors));
    }

    public PosterEntity savePoster(String title, String url, LocalDateTime fileAttachingDate, List<AuthorEntity> authors) {
        return posterDao.save(buildPoster(title, url, fileAttachingDate, authors));
    }

    public ScheduleEntity saveSchedule(ScheduleStatus status, List<ScheduleItemEntity> presentationsAndPosters) {
        return scheduleDao.save(buildSchedule(status, presentationsAndPosters));
    }

    public AddressEntity saveAddress(String city, String postalCode, String street, String streetNumber, Country country) {
        return addressDao.save(buildAddress(city, postalCode, street, streetNumber, country));
    }

    public OrganiserEntity saveOrganiser(String name, String email, String surname, List<ConferenceEntity> conferences) {
        return organiserDao.save(buildOrganiser(name, email, surname, conferences));
    }

    public GuestEntity saveGuest(String name, String email, String surname, List<ConferenceEntity> conferences) {
        return guestDao.save(buildGuest(name, email, surname, conferences));
    }

    public AuthorEntity saveAuthor(String name, String email, String surname, AcademicTitle academicTitle, List<ConferenceEntity> conferences) {
        return authorDao.save(buildAuthor(name, email, surname, academicTitle, conferences));
    }

    public ReviewerEntity saveReviewer(String name, String email, String surname, AcademicTitle academicTitle, List<ConferenceEntity> conferences) {
        return reviewerDao.save(buildReviewer(name, email, surname, academicTitle, conferences));
    }

    public RemarkEntity buildRemark(String remark) {
        return RemarkEntity.builder().remark(remark).build();
    }

    public ReviewEntity buildReview(ReviewerEntity reviewer, PosterEntity poster, PresentationEntity presentation,
                                    ReviewStatus status, LocalDateTime reviewDueDate, LocalDateTime reviewFinishDate, List<RemarkEntity> remarks) {
        return ReviewEntity.builder().reviewer(reviewer).poster(poster).presentation(presentation).status(status)
                .reviewDueDate(reviewDueDate).reviewFinishDate(reviewFinishDate).remarks(remarks).build();
    }

    public ConferenceInformationEntity buildConferenceInformation(String topic, String description, int maxNumberOfPresentations, int maxNumberOfSeats) {
        return ConferenceInformationEntity.builder().topic(topic).description(description)
                .maxNumberOfPresentations(maxNumberOfPresentations).maxNumberOfSeats(maxNumberOfSeats).build();
    }

    public ConferenceEntity buildConference(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime registrationDate,
                                            ConferenceInformationEntity conferenceInformation,
                                            List<OrganiserEntity> organisers, List<AuthorEntity> authors,
                                            List<GuestEntity> guests, ScheduleEntity schedule, AddressEntity address) {
        return ConferenceEntity.builder().startDate(startDate).endDate(endDate).registrationDate(registrationDate).cancelled(false)
                .conferenceInformation(conferenceInformation).organisers(organisers).address(address).guests(guests)
                .schedule(schedule).build();
    }

    public ScheduleItemEntity buildScheduleItem(String topic, String description, String localizationDetail,
                                                LocalDateTime startDate, LocalDateTime endDate,
                                                List<PosterEntity> posters, PresentationEntity presentation) {
        return ScheduleItemEntity.builder().topic(topic).description(description).localizationDetail(localizationDetail).startDate(startDate).endDate(endDate)
                .posters(posters).presentation(presentation).build();
    }

    public PresentationEntity buildPresentation(String title, String url, LocalDateTime fileAttachingDate, List<AuthorEntity> authors) {
        return PresentationEntity.builder().authors(authors).fileAttachingDate(fileAttachingDate).url(url).title(title).build();
    }

    public PosterEntity buildPoster(String title, String url, LocalDateTime fileAttachingDate, List<AuthorEntity> authors) {
        return PosterEntity.builder().authors(authors).fileAttachingDate(fileAttachingDate).url(url).title(title).build();
    }

    public ScheduleEntity buildSchedule(ScheduleStatus status, List<ScheduleItemEntity> presentationsAndPosters) {
        return ScheduleEntity.builder().presentationsAndPosters(presentationsAndPosters).status(status).build();
    }

    public AddressEntity buildAddress(String city, String postalCode, String street, String streetNumber, Country country) {
        return AddressEntity.builder().city(city).postalCode(postalCode).street(street).streetNumber(streetNumber).country(country).build();
    }

    public OrganiserEntity buildOrganiser(String name, String email, String surname, List<ConferenceEntity> conferences) {
        return OrganiserEntity.builder().name(name).email(email).password("$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy").conferences(conferences).surname(surname).role(roleOrganiser).build();
    }

    public GuestEntity buildGuest(String name, String email, String surname, List<ConferenceEntity> conferences) {
        return GuestEntity.builder().name(name).email(email).password("$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy").conferences(conferences).surname(surname).role(roleGuest).build();
    }

    public AuthorEntity buildAuthor(String name, String email, String surname, AcademicTitle academicTitle, List<ConferenceEntity> conferences) {
        return AuthorEntity.builder().name(name).email(email).password("$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy").conferences(conferences).academicTitle(academicTitle).banned(false).surname(surname).role(roleAuthor).build();
    }

    public ReviewerEntity buildReviewer(String name, String email, String surname, AcademicTitle academicTitle, List<ConferenceEntity> conferences) {
        return ReviewerEntity.builder().name(name).email(email).password("$2a$10$r8mAqL.VD7cLEh/i9Hl96.E2SYrNUuFauPxG3q4hKywigL8Qx94Vy").conferences(conferences).academicTitle(academicTitle).surname(surname).role(roleReviewer).build();
    }

    private void saveRoles() {
        roleOrganiser = roleDao.save(RoleEntity.builder().name(UserRoleName.ROLE_ORGANISER).build());
        roleGuest = roleDao.save(RoleEntity.builder().name(UserRoleName.ROLE_GUEST).build());
        roleAuthor = roleDao.save(RoleEntity.builder().name(UserRoleName.ROLE_AUTHOR).build());
        roleReviewer = roleDao.save(RoleEntity.builder().name(UserRoleName.ROLE_REVIEWER).build());
    }

    private void saveCurrency() {
        pricePlnStudent = currencyValueDao.save(CurrencyValueEntity.builder().amount(new BigDecimal(15)).currency(Currency.PLN).type(ValueType.STUDENT).build());
        pricePlnStandard = currencyValueDao.save(CurrencyValueEntity.builder().amount(new BigDecimal(30)).currency(Currency.PLN).type(ValueType.STANDARD).build());
        pricePLNVip = currencyValueDao.save(CurrencyValueEntity.builder().amount(new BigDecimal(99)).currency(Currency.PLN).type(ValueType.VIP).build());
    }
}
