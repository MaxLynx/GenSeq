package edu.dnaprocessing.utils.dna;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.dnaprocessing.sequence.dna.DNASequence;

@RestController
@RequestMapping(value="/dnautils")
public class DNAUtilsController {
	
	@Autowired
	private DNAUtilsService dnaUtilsService;
	
	@RequestMapping(method=RequestMethod.POST, value="/filter")
	public DNASequence filterDNASequence(@RequestBody DNASequence dnaSequence){
		return dnaUtilsService.filter(dnaSequence);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/random/{length}")
	public DNASequence randomDNASequence(@PathVariable int length){
		return dnaUtilsService.random(length);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/complement")
	public DNASequence complementDNASequence(@RequestBody DNASequence dnaSequence){
		return dnaUtilsService.complement(dnaSequence);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/measurebasepercentages")
	public Pair<Double, Double> measureBasePairPercentagesInDNASequence(
			@RequestBody DNASequence dnaSequence){
		return dnaUtilsService.measureBasePairPercentages(dnaSequence);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/mutate/{percentage}")
	public DNASequence mutateDNASequence(@RequestBody DNASequence dnaSequence, 
			@PathVariable int percentage){
		return dnaUtilsService.mutate(dnaSequence, percentage);
	}
	
}
