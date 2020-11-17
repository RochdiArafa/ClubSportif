package exemple.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hp.hpl.jena.rdf.model.Model;
import tools.JenaEngine;

@RestController
@RequestMapping("/api/sport")
public class SportController {
//Get All Teams By Categorie "FootBall , HandBall ..."
	@GetMapping("/SportTeams/{sporttype}")
	public String getFootBallTeams(@PathVariable String sporttype) {
		String NS = "";
		// lire le model a partir d'une ontologie
		Model model = JenaEngine.readModel("data/Sports.owl");
		if (model != null) {
			// lire le Namespace de l’ontologie
			NS = model.getNsPrefixURI("");
			// apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
			// query on the model after inference

			return JenaEngine.executeQueryFile(inferedModel,
					"PREFIX ns: <http://www.semanticweb.org/ghaithbelkhir/ontologies/2020/10/DomaineSports#> \r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
							+ "SELECT distinct?equipe \r\n" + "WHERE { \r\n"
							+ "?equipe rdf:type ns:Equipe-"+ sporttype + ". \r\n" + 
							"}");

		} else {
			return "Error when reading model from ontology";
		}
	}
	// Get All FootBall Teams with Details
	@GetMapping("/teamsDetails/{teamName}")
	public String getTeamsDetails(@PathVariable String teamName) {
		String NS = "";
		// lire le model a partir d'une ontologie
		Model model = JenaEngine.readModel("data/Sports.owl");
		if (model != null) {
			// lire le Namespace de l’ontologie
			NS = model.getNsPrefixURI("");
			// apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
			// query on the model after inference

			return JenaEngine.executeQueryFile(inferedModel,
					"PREFIX ns: <http://www.semanticweb.org/ghaithbelkhir/ontologies/2020/10/DomaineSports#>\r\n" + 
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
					"PREFIX DomaineSports: <http://www.semanticweb.org/ghaithbelkhir/ontologies/2020/10/DomaineSports#>\r\n" + 
					"SELECT  distinct ?equipe ?name ?Nombre_Joueurs\r\n" + 
					"	WHERE { ?equipe rdf:type ns:Equipe-FootBall.\r\n" + 
					"	                   ?equipe ns:Nom_Equipe ?name .\r\n" + 
					"	                   ?equipe ns:Nombre_Joueurs ?Nombre_Joueurs .\r\n" +
					"FILTER (?name = \"" + teamName + "\" )" +
					"	}	");

		} else {
			return "Error when reading model from ontology";
		}
	}
	// Get All Available Sport in our palteforme
	@GetMapping("/SportsType")
	public String getAllSports() {
		String NS = "";
		// lire le model a partir d'une ontologie
		Model model = JenaEngine.readModel("data/Sports.owl");
		if (model != null) {
			// lire le Namespace de l’ontologie
			NS = model.getNsPrefixURI("");
			// apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
			// query on the model after inference

			return JenaEngine.executeQueryFile(inferedModel,
					"PREFIX ns: <http://www.semanticweb.org/ghaithbelkhir/ontologies/2020/10/DomaineSports#> \r\n"
							+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \r\n"
							+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \r\n"
							+ "SELECT distinct?typesport \r\n" + "WHERE { \r\n"
							+ "?typesport rdfs:subClassOf ns:Sports . \r\n" + "}");

		} else {
			return "Error when reading model from ontology";
		}
	}
// Find Player By Nom et Prenom with Details
	@GetMapping("/findPlayer/{Nom}/{Prenom}")
	public String findPlayer(@PathVariable String Nom, @PathVariable String Prenom) {
		String NS = "";
		// lire le model a partir d'une ontologie
		Model model = JenaEngine.readModel("data/Sports.owl");
		if (model != null) {
			// lire le Namespace de l’ontologie
			NS = model.getNsPrefixURI("");
			// apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
			// query on the model after inference
			String query = "PREFIX ns: <http://www.semanticweb.org/ghaithbelkhir/ontologies/2020/10/DomaineSports#>\r\n" + 
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"SELECT  distinct ?Joueur ?Nom  ?Prenom ?Age ?Nationalite ?equipe\r\n" + 
					"	WHERE { ?Joueur rdf:type ns:Joueur ."
					+ "?Joueur ns:Nom ?Nom .?Joueur ns:Prenom ?Prenom ."
					+ "?Joueur ns:Nationalite ?Nationalite . "
					+ "?Joueur ns:Age ?Age .?Joueur ns:Jouer ?equipe ."
					+ "FILTER (?Nom = \"" + Nom + "\" && ?Prenom = \""+Prenom+"\")" + 
					"	} ";
			return JenaEngine.executeQueryFile(inferedModel, query);
		} else {
			return "Error when reading model from ontology";
		}
	}
	// Get All Players with all details
	@GetMapping("/AllPlayers")
	public String getAllPlayers() {
		String NS = "";
		// lire le model a partir d'une ontologie
		Model model = JenaEngine.readModel("data/Sports.owl");
		if (model != null) {
			// lire le Namespace de l’ontologie
			NS = model.getNsPrefixURI("");
			// apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
			// query on the model after inference

			return JenaEngine.executeQueryFile(inferedModel,
					"PREFIX ns: <http://www.semanticweb.org/ghaithbelkhir/ontologies/2020/10/DomaineSports#>\r\n" + 
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
					"\r\n" + 
					"SELECT  distinct ?Joueur ?Nom  ?Prenom ?Age ?Nationalite ?equipe\r\n" + 
					"	WHERE { ?Joueur rdf:type ns:Joueur .\r\n" + 
					"	                   ?Joueur ns:Nom ?Nom .\r\n" + 
					"	                   ?Joueur ns:Prenom ?Prenom .\r\n" + 
					"                                           ?Joueur ns:Nationalite ?Nationalite .\r\n" + 
					"                                           ?Joueur ns:Age ?Age .\r\n" + 
					"                                           ?Joueur ns:Jouer ?equipe .\r\n" + 
					"	}");

		} else {
			return "Error when reading model from ontology";
		}
	}
	// Find Team By name with all Details
	@GetMapping("/findTeams/{teamName}")
	public String findTeam(@PathVariable String teamName) {
		String NS = "";
		// lire le model a partir d'une ontologie
		Model model = JenaEngine.readModel("data/Sports.owl");
		if (model != null) {
			// lire le Namespace de l’ontologie
			NS = model.getNsPrefixURI("");
			// apply our rules on the owlInferencedModel
			Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
			// query on the model after inference

			return JenaEngine.executeQueryFile(inferedModel,
					"PREFIX ns: <http://www.semanticweb.org/ghaithbelkhir/ontologies/2020/10/DomaineSports#>\r\n" + 
					"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n" + 
					"PREFIX owl: <http://www.w3.org/2002/07/owl#>\r\n" + 
					"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
					"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\r\n" + 
					"\r\n" + 
					"SELECT  distinct ?equipe ?name ?Nombre_Joueurs\r\n" + 
					"	WHERE { ?equipe rdf:type ns:Equipe-FootBall.\r\n" + 
					"	                   ?equipe ns:Nom_Equipe ?name .\r\n" + 
					"	                   ?equipe ns:Nombre_Joueurs ?Nombre_Joueurs .\r\n" + 
					"                                           FILTER (?Name = \"" + teamName + "\" )\r\n" + 
					"	}	");
			
		} else {
			return "Error when reading model from ontology";
		}
	}
}
