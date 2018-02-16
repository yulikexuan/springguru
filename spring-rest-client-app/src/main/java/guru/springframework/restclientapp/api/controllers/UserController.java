//: guru.springframework.restclientapp.api.controllers.UserController.java


package guru.springframework.restclientapp.api.controllers;


import guru.springframework.restclientapp.api.domain.services.IApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ServerWebExchange;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Slf4j
@Controller
public class UserController {

	private static final String RESPONSE_JSON = "{ \"data\": [ { \"gender\": \"female\", \"name\": { \"title\": \"Mrs.\", \"first\": \"Jennyfer\", \"last\": \"Gottlieb\" }, \"location\": { \"street\": \"55256 O'Reilly Divide Apt. 278\", \"city\": \"West Sheilamouth\", \"state\": \"Nevada\", \"postcode\": \"41126-9388\" }, \"email\": \"janis.reinger@abshire.com\", \"login\": { \"username\": \"tristin.zemlak\", \"password\": \"hph8t9EuDMj\", \"md5\": \"563aa0983e3cad3c2f378a4f4f733a14\", \"sha1\": \"0c7794d4dc903701cf0adcc9947abd6863d850d1\", \"sha256\": \"f0816090b7e893e795de3d8e3928e166aab7967ef54bf54f1835788d13ce4833\" }, \"phone\": \"(888) 738-5992\", \"job\": { \"title\": \"Product Specialist\", \"company\": \"Zieme Group\" }, \"billing\": { \"card\": { \"type\": \"Visa\", \"number\": \"5188060750192699\", \"expiration_date\": { \"date\": \"2020-01-27 12:17:35.000000\", \"timezone_type\": 3, \"timezone\": \"UTC\" }, \"iban\": \"FM60538114080814440786992851\", \"swift\": \"SWGUFYTS\" } }, \"language\": \"ho\", \"currency\": \"SDG\" }, { \"gender\": \"male\", \"name\": { \"title\": \"Miss\", \"first\": \"Cassidy\", \"last\": \"Hilll\" }, \"location\": { \"street\": \"5948 Harvey Ranch\", \"city\": \"Port Cathrynfort\", \"state\": \"New York\", \"postcode\": \"59387-8626\" }, \"email\": \"king.koelpin@gmail.com\", \"login\": { \"username\": \"stanley.shields\", \"password\": \"n%/&+k#\", \"md5\": \"f183f4823cd7f869b374ee405e1308a2\", \"sha1\": \"8f975a335bb499f69784d17a4f900d3b5380e683\", \"sha256\": \"cc3f18dc155d9c2b4d5c31cb1371fab9d6d0866006ce9aece8a59a17039bf892\" }, \"phone\": \"(800) 935-7648\", \"job\": { \"title\": \"Pantograph Engraver\", \"company\": \"Purdy Ltd\" }, \"billing\": { \"card\": { \"type\": \"Visa\", \"number\": \"4716750458205140\", \"expiration_date\": { \"date\": \"2019-04-02 20:56:26.000000\", \"timezone_type\": 3, \"timezone\": \"UTC\" }, \"iban\": \"GY19806597592142903692014599\", \"swift\": \"SPMHJC0Q62O\" } }, \"language\": \"sv\", \"currency\": \"HUF\" }, { \"gender\": \"female\", \"name\": { \"title\": \"Ms.\", \"first\": \"Marianna\", \"last\": \"Brekke\" }, \"location\": { \"street\": \"6148 Derrick Shore Suite 560\", \"city\": \"East Domenickfort\", \"state\": \"Idaho\", \"postcode\": \"88225\" }, \"email\": \"umosciski@gmail.com\", \"login\": { \"username\": \"mbailey\", \"password\": \"[:q\\\\ws4mp']&y^=$)GqQ\", \"md5\": \"cf50c90697f41f37fdb7e12c2d4bb29d\", \"sha1\": \"8d5b8568a70971d7259146c23eb726168539d05f\", \"sha256\": \"94fb96f58337e8ebe93b50f4259da42f58c0a4a44b2e8738458f1d917f1616bf\" }, \"phone\": \"844-630-6125\", \"job\": { \"title\": \"Fish Game Warden\", \"company\": \"Blick-Smitham\" }, \"billing\": { \"card\": { \"type\": \"MasterCard\", \"number\": \"5140673905061184\", \"expiration_date\": { \"date\": \"2018-05-11 20:48:15.000000\", \"timezone_type\": 3, \"timezone\": \"UTC\" }, \"iban\": \"QA57378330394514948684049730\", \"swift\": \"WXVSLGV3\" } }, \"language\": \"ka\", \"currency\": \"KWD\" }, { \"gender\": \"male\", \"name\": { \"title\": \"Prof.\", \"first\": \"Gerda\", \"last\": \"Upton\" }, \"location\": { \"street\": \"9527 Cruickshank Glen Apt. 344\", \"city\": \"Jenkinsville\", \"state\": \"Connecticut\", \"postcode\": \"68374-6313\" }, \"email\": \"rconsidine@yahoo.com\", \"login\": { \"username\": \"lennie.turcotte\", \"password\": \"51:7dY\\\\|PLu8_Nf(\", \"md5\": \"d346be5d1b8198e6615d0ad6c5cffc3a\", \"sha1\": \"0d20358da961bd9643b26bba38470d7b8d69e848\", \"sha256\": \"0c0bf26f35c93ebdc47788201bca8c911c97a9582dce7fe5376c517a0e01f638\" }, \"phone\": \"877.650.9880\", \"job\": { \"title\": \"Gas Pumping Station Operator\", \"company\": \"Johnston-Pfeffer\" }, \"billing\": { \"card\": { \"type\": \"MasterCard\", \"number\": \"4260606043283689\", \"expiration_date\": { \"date\": \"2019-10-13 10:36:08.000000\", \"timezone_type\": 3, \"timezone\": \"UTC\" }, \"iban\": \"SC35419023310661414341647308\", \"swift\": \"VWJRRN7G777\" } }, \"language\": \"az\", \"currency\": \"BMD\" } ] }";

	private final IApiService apiService;

	@Autowired
	public UserController(IApiService apiService) {
		this.apiService = apiService;
	}

	@ResponseBody
	@RequestMapping(value="/api/users", method=GET)
	public String getUsersData() {
		return RESPONSE_JSON;
	}

	@GetMapping({"", "/", "/index"})
	public String index() {
		return "index";
	}

	/*
	 * Contract for an HTTP request-response interaction
	 * - Provides access to the HTTP request and response and also exposes
	 *   additional server-side processing related properties and features
	 *   such as request attributes
	 */
	@PostMapping("/users")
	public String userList(Model model, ServerWebExchange serverWebExchange) {

//		MultiValueMap<String, String> map = serverWebExchange.getFormData()
//				.block();
//
//		Integer limit = new Integer(map.get("limit").get(0));
//
//		log.debug(">>>>>>> Received limit-value: " + limit);
//
////		if (limit == null) {
////			log.debug("Setting limit to  default value: " + 3);
////			limit = 3;
////		}
//
//		log.debug(">>>>>>> Setting limit to  default value: " + 3);
//		limit = 3;
//
//		model.addAttribute("users", this.apiService.getUsers(limit));

		model.addAttribute("users",
				this.apiService.getUsers(
						serverWebExchange
								.getFormData()
								.map(d ->
										new Integer(d.getFirst("limit")))));

		return "userlist";

	}// End of userList

}///~