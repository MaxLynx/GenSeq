package edu.dnaprocessing.sequence.protein;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proteinsequences")
public class ProteinSequenceController {
	
	@Autowired
	private ProteinSequenceService proteinSequenceService;
	
	@RequestMapping("")
	public List<ProteinSequence> getProteinSequences(){
		return proteinSequenceService.getProteinSequences();
	}
	
	@RequestMapping("/{id}")
	public ProteinSequence getProteinSequence(@PathVariable String id){
		return proteinSequenceService.getProteinSequence(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="")
	public void setProteinSequence(@RequestBody ProteinSequence proteinSequence){
		proteinSequenceService.setGeneticSequence(proteinSequence.normalize());
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public void updateProteinSequence(@PathVariable String id, 
			@RequestBody ProteinSequence proteinSequence){
		proteinSequenceService.setGeneticSequence(id, proteinSequence.normalize());
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void removeProteinSequence(@PathVariable String id){
		proteinSequenceService.removeGeneticSequence(id);
	}
}
