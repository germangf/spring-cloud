package ch.ggf.resource.avaloq.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.Container;
import ch.ggf.common.domain.Manager;
import ch.ggf.common.domain.Profitability;
import ch.ggf.common.domain.specialcondition.Group;
import ch.ggf.common.util.Dates;
import ch.ggf.resource.avaloq.service.AvaloqService;

@RestController
@CrossOrigin
@RequestMapping(value = "/avaloq", method = RequestMethod.GET)
public class AvaloqController {

  @Autowired
  private AvaloqService avaloqService;

  @RequestMapping("/")
  public String home() {
    return "Avaloq Resource is working!";
  }

  @RequestMapping("/hello")
  public String hello() {
    return "Hello from Avaloq Resource!";
  }

  // group #special-condition
  @RequestMapping("/group")
  public List<Group> getGroups() {
    return avaloqService.getGroups();
  }

  // bp #special-condition
  @RequestMapping("/bp")
  public List<BP> getBPs() {
    return avaloqService.getBPs();
  }

  // container #special-condition
  @RequestMapping("/container")
  public List<Container> getContainers() {
    return avaloqService.getContainers();
  }

  // bp #credit
  /**
   * <p>
   * Returns a given container.
   * <p>
   *
   * @param bpNr
   *          the container id to be evaluated
   * @return a {@code Container} object list
   */
  @RequestMapping("/bp/{bpNr}")
  public BP getBP(@PathVariable("bpNr") String bpNr) {
    return avaloqService.getBP(bpNr);
  }

  // bp (nrs) for manager
  /**
   * <p>
   * Returns BP ids for a manager and active after a date.
   * <p>
   *
   * @param managerAlias
   *          the manager to be evaluated
   * @param date
   *          date to gather the data
   * @return a {@code Integer} BP ids list
   */
  @RequestMapping("/manager/{windowsManager}/bp")
  public List<String> getBPNrsForManager(@PathVariable("windowsManager") String windowsManager, @RequestParam(name = "active-after", required = false) String activeAfter) {
    return avaloqService.getBPNrsForManager(windowsManager, parseDateIfNotNull(activeAfter));
  }

  // bp (profitability) for bpNr #credit
  @RequestMapping(value = "/bp/{bpNr}/profitability")
  public Profitability profitability(@PathVariable("bpNr") String bpNr) {
    return avaloqService.profitability(bpNr);
  }

  // bp (alias) for containerNr #claim
  /**
   * <p>
   * Returns the BP whose container belongs.
   * <p>
   *
   * Each BP has its containers, {@link ch.ggf.Container.domain.Portfolio}.<br>
   *
   * @param containerNr
   *          the container to be evaluated
   * @return a {@code String} BP aliasmanager username
   */
  @RequestMapping(value = "/container/{containerNr}/bpalias")
  public BP getBPForContainer(@PathVariable("containerNr") String containerNr) {
    return avaloqService.getBPAliasForContainer(containerNr);
  }

  // container #credit
  @RequestMapping("/container/{containerNr}")
  public Container getContainer(@PathVariable("containerNr") String containerNr) {
    return avaloqService.getContainer(containerNr);
  }

  // container (nrs) for bp
  @RequestMapping("/bp/{bpNr}/container")
  public List<String> getContainerNrsForBP(@PathVariable("bpNr") String bpNr, @RequestParam(name = "active-after", required = false) String activeAfter) {
    return avaloqService.getContainerNrsForBP(bpNr, parseDateIfNotNull(activeAfter));
  }

  // container (nrs) for manager
  @RequestMapping("/manager/{manager}/container")
  public List<String> getContainerNrsForManager(@PathVariable("manager") String manager, @RequestParam(name = "active-after", required = false) String activeAfter) {
    return avaloqService.getContainerNrsForManager(manager, parseDateIfNotNull(activeAfter));
  }

  // container (nr) for encryptedKey #cif-statement #m720-statement
  /**
   * Returns the containerNumber given an encrypted key.
   *
   * @param encryptedKey
   *          the encryptedKey
   * @return a {@code String} containerNumber info
   *
   */
  @RequestMapping("/container/{encryptedKey}/container-number")
  @ResponseBody
  public String findContainerNumber(@PathVariable("encryptedKey") String encryptedKey) {
    return avaloqService.findContainerNumber(encryptedKey);
  }

  // container (manager) #claim
  /**
   * <p>
   * Returns the manager who manages the container.
   * <p>
   *
   * Each manager, {@link ch.ggf.avaloqdb.domain.Manager}, has associated its BPs
   * ({@link ch.ggf.BP.domain.Account}).<br>
   * Each account has its containers,
   * {@link ch.ggf.Container.domain.Portfolio}.<br>
   *
   * @param container
   *          the container to be evaluated
   * @return a {@code String} manager username
   */
  @RequestMapping(value = "/container/{container}/manager")
  public Manager findManagerFromContainer(@PathVariable("container") String container) {
    return avaloqService.findManagerFromContainer(container);
  }

  // container (nostro) #claim
  @RequestMapping("/container/{containerNr}/nostro")
  public Container getContainerNostro(@PathVariable("containerNr") String containerNr) {
    return avaloqService.getContainerNostro(containerNr);
  }

  // container (language) #cif-statement #m720-statement
  /**
   * Returns the container's language.
   *
   * @param container
   *          the container to be evaluated
   * @return the {@code String} language
   *
   */
  @CrossOrigin
  @RequestMapping("/container/{containerNr}/language")
  public String getContainerLanguage(@PathVariable("containerNr") String containerNr) {
    return avaloqService.getContainerLanguage(containerNr);
  }

  // container (holders) #cif-statement #m720-statement
  /**
   * Returns the accountHolder info given container.
   *
   * @param validDate
   *          the minimum Date from which the user valid is
   * @param container
   *          the container to be evaluated
   * @return a {@code Container} container info
   *
   */
  @RequestMapping("/container/{validDate}/{containerNr}/account-holders")
  public Container findContainerAccountHolders(@PathVariable("validDate") String validDate, @PathVariable("containerNr") String containerNr) {
    return avaloqService.findContainerAccountHolders(validDate, containerNr);
  }

  // ---------------------------
  // others

  // #claim
  /**
   * <p>
   * Returns the CHF rate from a given currency.
   * <p>
   *
   * The result will be the value of 1 CHF in this particular currency.<br>
   *
   * @param currency
   *          whose value against CHF will be returned
   * @return a {@code Double} rate against CHF
   */
  @RequestMapping(value = "/amount/{amount}/currency/{currency}/chf")
  public Double amountInCHF(@PathVariable("amount") Double amount, @PathVariable("currency") String currency) {
    return avaloqService.findCHFRate(currency) * amount;
  }

  /**
   * Returns EUR deductible expenses given a container.
   *
   * @param container
   *          to be evaluated
   * @return a {@code Double} value
   *
   */
  @RequestMapping(value = "/container/{containerNr}/deductible-expenses")
  public Double getDeductibleExpenses(@PathVariable("containerNr") String containerNr) {
    Double d = avaloqService.getDeductibleExpenses(containerNr);
    return d;
  }

  // #credit
  /**
   * Returns cross rate given a currency pair.
   *
   * @param currencyBase
   *          the base currency
   * @param currencyTarget
   *          the target currency
   * @return a {@code Double} rate
   */
  @RequestMapping(value = "/xrate/{currencyBase}/{currencyTarget}")
  public Double crossRate(@PathVariable("currencyBase") String currencyBase, @PathVariable("currencyTarget") String currencyTarget) {
    return avaloqService.crossRate(currencyBase, currencyTarget);
  }

  /**
   * <p>
   * Returns all managers ids stored in Avaloq.
   * <p>
   *
   * Each manager, {@link ch.ggf.avaloqdb.domain.Manager}, has associated its
   * accounts ({@link ch.ggf.BP.domain.Account}).<br>
   * Each account has its portfolios,
   * {@link ch.ggf.Container.domain.Portfolio}.<br>
   *
   * @return a {@code String} object list
   */
  @RequestMapping("/windows/manager")
  public List<String> getWindowsManagers() {
    return avaloqService.getWindowsManagers();
  }

  @RequestMapping(value = "/manager/citrix-name/{citrixName}/windows-name")
  public String mappingCitrixName(@PathVariable("citrixName") String citrixName) {
    return avaloqService.mappingCitrixName(citrixName);
  }

  private Date parseDateIfNotNull(String dateString) {
    Date date = null;
    if (dateString != null) {
      date = Dates.parse(dateString);
    }
    return date;
  }

}
