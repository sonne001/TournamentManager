package org.dos.tournament.player;

import org.dos.tournament.player.utils.IParticipantId;
import org.dos.tournament.player.utils.ParticipantStatus;

public interface IParticipant
{
  public IParticipantId getParticipantId();
  public void setParticipantId(IParticipantId id);
  
  public String getCode();
  public String getName();
  public String getDescription();
  
  public ParticipantStatus getStatus();
  public void setStatus(ParticipantStatus status);
  public boolean activateParticipant();
  public boolean inactivateParticipant();
  public boolean disqualifyParticipant();
  
  public boolean hasAttendees();
  public Attendee[] getAttendeesToArray();
}