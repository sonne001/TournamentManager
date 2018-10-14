package org.dos.tournament.petanque.result;

public class PetanqueSuperMeleeClubChampionshipResult extends PetanqueMatchResult
{

  public PetanqueSuperMeleeClubChampionshipResult(int score, int oppsScore)
  {
    super(score, oppsScore);
  }

  protected int compileGameScore()
  {
    return (0 < this.getDifference()?3:1);
  }

}
