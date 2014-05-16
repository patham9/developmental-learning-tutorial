package reactive;

import coupling.Experience;
import coupling.Intention;
import coupling.Intention1;
import coupling.Obtention;
import coupling.Obtention2;
import coupling.Obtention3;
import coupling.Result;
import coupling.interaction.Interaction3;
import existence.Existence1;

/**
 * A Reality4 is a sort of Reality3 that expects an Intention1 which specifies an experience,
 * and results in an Obtention3 which provides an enacted Interaction.
 * It results in R2 when the current experience equals the previous experience and differs from the penultimate experience.
 * and in R21 otherwise.
 * e1->r1 e1->r2 e2->r1 e2->r2 etc. 
 * 
 * Reality4 is used to demonstrate an Existence capable of anticipating two steps to make a decision.
 * 
 * @author Olivier
 */
public class Environment4 extends Environment3 {
	
	protected Experience penultimateExperience;

	public Environment4(Existence1 existence){
		super(existence);
	}

	@Override
	public Obtention3 provideObtention(Intention intention){

		Experience experience = ((Intention1)intention).getExperience();
		Result result = giveResult(experience);
		Interaction3 enactedInteraction = (Interaction3)this.existence.createOrGetPrimitiveInteraction(experience, result, 0);
		
		return new Obtention3(enactedInteraction);
	}

	public Result giveResult(Experience experience){
		
		Result result = this.existence.createOrGetResult(this.existence.LABEL_R1);

		if (this.penultimateExperience != experience &&
			this.previousExperience == experience)
			result =  this.existence.createOrGetResult(this.existence.LABEL_R2);
		
		this.penultimateExperience = this.previousExperience;
		this.previousExperience = experience;
		
		return result;
	}
}