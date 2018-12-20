package edu.dnaprocessing.sequence.rna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rnasequences")
public class RNASequenceController {
	
	@Autowired
	private RNASequenceService rnaSequenceService;
	
	@RequestMapping("")
	public List<RNASequence> getRNASequences(){
		return rnaSequenceService.getRNASequences();
	}
	
	@RequestMapping("/{id}")
	public RNASequence getRNASequence(@PathVariable String id){
		return rnaSequenceService.getRNASequence(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public void setRNASequence(@RequestBody RNASequence rnaSequence){
		rnaSequenceService.setGeneticSequence(rnaSequence.normalize());
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public void updateRNASequence(@PathVariable String id, @RequestBody RNASequence rnaSequence){
		rnaSequenceService.setGeneticSequence(id, rnaSequence.normalize());
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void removeRNASequence(@PathVariable String id){
		rnaSequenceService.removeGeneticSequence(id);
	}
}
