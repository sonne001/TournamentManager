package org.dos.tournament.petanque.team;

import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Vector;

import org.dos.tournament.petanque.result.PetanqueMatchResult;
import org.dos.tournament.player.AssociationAttendee;
import org.dos.tournament.result.AbstractTotalResult;
import org.dos.tournament.result.IResult;

public class JoueurIndividuel extends AssociationAttendee
{
  public JoueurIndividuel(int id, String name, String association)
  {
    super(id, name, association);
    this.result = new PetanqueTotalResult();
  }
  
 

  /* (non-Javadoc)
   * @see org.dos.tournament.player.AbstractParticipant#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(Object o)
  {
    return this.result.compareTo(((JoueurIndividuel)o).result);
  }



  private class PetanqueTotalResult extends AbstractTotalResult
  {
    public final static int POINTS      = 0;
    public final static int DIFFERENCE  = 1;
    public final static int WINS        = 2;
    public final static int MATCHES     = 3;
    
    @Override
    public int getTotalScore()
    {
      Iterator<IResult> it = this.mapResults.values().iterator();
      int _retval = 0;
      while(it.hasNext())
        _retval += ((PetanqueMatchResult)it.next()).getMatchScore();
      return _retval;
    }
    
    /* (non-Javadoc)
     * @see org.dos.tournament.result.ITotalResult#getTotalResult()
     */
    @Override
    public int[] getTotalResult()
    {
      int[] _retval = { this.getTotalScore(), this.getTotalDifference(), this.countWins(), this.countMatches() };
      return _retval;
    }
    
    @Override
    public Collection<Object> getTotalResultIdentifiers()
    {
      Collection<Object> _retval = new Vector<Object>();
      _retval.add(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("PetanqueTotalResult.points.text"));
      _retval.add(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("PetanqueTotalResult.difference.text"));
      _retval.add(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("PetanqueTotalResult.wins.text"));
      _retval.add(ResourceBundle.getBundle("org.dos.tournament.resources.messages.messages").getString("PetanqueTotalResult.matches.text"));
      return _retval;
    }



    public int getTotalDifference()
    {
      Iterator<IResult> it = this.mapResults.values().iterator();
      int _retval = 0;
      while(it.hasNext())
        _retval += ((PetanqueMatchResult)it.next()).getDifference();
      return _retval;
    }

    public int countWins()
    {
      Iterator<IResult> it = this.mapResults.values().iterator();
      int _retval = 0;
      while(it.hasNext())
        _retval += (0 < ((PetanqueMatchResult)it.next()).getDifference())?1:0;
      return _retval;
    }


    @Override
    public int getValueForCategory(int category)
    {
      int _retval = -1;
      
      switch(category)
      {
        case PetanqueTotalResult.POINTS :     _retval = this.getTotalScore();
                                              break;
        case PetanqueTotalResult.DIFFERENCE : _retval = this.getTotalDifference();
                                              break;
        case PetanqueTotalResult.WINS :       _retval = this.countWins();
                                              break;
        case PetanqueTotalResult.MATCHES :    _retval = this.countMatches();
                                              break;
      }
      
      return _retval;
    }
    
    public String getName()
    {
      return JoueurIndividuel.this.getName();
    }

    @Override
    public int compareTo(Object o)
    {
      return this.compareTo((PetanqueTotalResult) o, 0);
    }

    private int compareTo(PetanqueTotalResult opps, int category)
    {
      switch(category)
      {
        case PetanqueTotalResult.POINTS :     
        case PetanqueTotalResult.DIFFERENCE : 
        case PetanqueTotalResult.WINS :       
        case PetanqueTotalResult.MATCHES :    return(this.getValueForCategory(category) == opps.getValueForCategory(category)?this.compareTo(opps, category+1):this.getValueForCategory(category) - opps.getValueForCategory(category));
        default:                              return (-1)*this.getName().compareToIgnoreCase(opps.getName());
      }
    }

  }

}
