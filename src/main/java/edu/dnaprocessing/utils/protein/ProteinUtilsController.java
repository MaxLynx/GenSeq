package edu.dnaprocessing.utils.protein;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.dnaprocessing.sequence.protein.ProteinSequence;

@RestController
@RequestMapping(value="/proteinutils")
public class ProteinUtilsController {
	
	@Autowired
	private ProteinUtilsService proteinUtilsService;
	
	@RequestMapping(method=RequestMethod.POST, value="/filter")
	public ProteinSequence filterProteinSequence(@RequestBody ProteinSequence proteinSequence){
		return proteinUtilsService.filter(proteinSequence);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/random/{length}")
	public ProteinSequence randomProteinSequence(@PathVariable int length){
		return proteinUtilsService.random(length);
	}
	
}
