package edu.dnaprocessing.sequence.dna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dnasequences")
public class DNASequenceController {
	
	@Autowired
	private DNASequenceService dnaSequenceService;
	
	@RequestMapping("")
	public List<DNASequence> getDNASequences(){
		return dnaSequenceService.getDNASequences();
	}
	
	@RequestMapping("/{id}")
	public DNASequence getDNASequence(@PathVariable String id){
		return dnaSequenceService.getDNASequence(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public void setDNASequence(@RequestBody DNASequence dnaSequence){
		dnaSequenceService.setGeneticSequence(dnaSequence.normalize());
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public void updateDNASequence(@PathVariable String id, @RequestBody DNASequence dnaSequence){
		dnaSequenceService.setGeneticSequence(id, dnaSequence.normalize());
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void removeDNASequence(@PathVariable String id){
		dnaSequenceService.removeGeneticSequence(id);
	}
}
