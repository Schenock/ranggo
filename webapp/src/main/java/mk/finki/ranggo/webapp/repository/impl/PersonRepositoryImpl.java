package mk.finki.ranggo.webapp.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import mk.finki.ranggo.webapp.model.Person;
import mk.finki.ranggo.webapp.repository.PersonRepositoryAuxiliary;

@Repository
public class PersonRepositoryImpl implements PersonRepositoryAuxiliary {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Person findByName(String name) {
		if(name != null && !name.isEmpty()){
			return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), Person.class);
		}else{
			//name can not be null
			return null;
		}
	}
	
	public Person findByDbpediaUrl(String dbpediaUrl) {
		if(dbpediaUrl != null){
			return mongoTemplate.findOne(new Query(Criteria.where("dbpediaUrl").is(dbpediaUrl)), Person.class);
		}else{
			//dbpediaUrl can not be null
			return null;
		}
	}
	
	public List<Person> findAll(){
		Query query = new Query();
		return mongoTemplate.find(query, Person.class);
	}

	public Person findByID(String id) {
		// TODO Auto-generated method stub
		if(id != null && !id.isEmpty()){
			return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Person.class);
		}
		return null;
	}
}