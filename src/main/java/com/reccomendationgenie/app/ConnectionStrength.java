package com.reccomendationgenie.app;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ConnectionStrength
{

  private static final int INDUSTRY_SCORE     = 20;
  private static final int EDUCATION_SCORE    = 30;
  private static final int POSITION_SCORE     = 30;
  private static final int HIGHER_SKILL_SCORE = 20;
  private static final int LOWER_SKILL_SCORE  = 10;

  /*
   * This method calculates the connection strength between two members based on
   * industry,education,positions,skills, etc that they share in common
   * 
   * Params: Member1, member2
   * 
   * Return: Connection strength %
   */
  public static Double getConnectionStrength(Member memberId1, Member memberId2) throws Exception
  {
    Double connStrength = 0.0;

    if (memberId1 == null)
    {
      throw new Exception("memberId1 is empty");
    }

    if (memberId2 == null)
    {
      throw new Exception("memberId2 is empty");
    }

    // Compare industries
    if (memberId1.industry != null && memberId2.industry != null && memberId1.industry.trim().equalsIgnoreCase(memberId2.industry))
    {
      connStrength += connStrength + INDUSTRY_SCORE;
    }

    if(memberId1.educations != null 
        && memberId2.educations != null &&  memberId1.educations.values != null &&  memberId2.educations.values != null) {
      List<ValueObject> educations1 = memberId1.educations.values;
      List<ValueObject> educations2 = memberId2.educations.values;

      int eduSize1 = educations1.size();
      int eduSize2 = educations2.size();
      int commonEduSize = 0;

      // Compare schools
      for (ValueObject edu1 : educations1)
      {

        for (ValueObject edu2 : educations2)
        {

          if (!StringUtils.isBlank(edu1.schoolName)
              && !StringUtils.isBlank(edu2.schoolName)
              && edu1.schoolName.trim().equalsIgnoreCase(edu2.schoolName.trim()))
          {

            commonEduSize++;

          }
        }

      }

      Double eduStrength =
          (new Double(commonEduSize) / new Double(Math.max(eduSize1, eduSize2)));

      System.out.println("eduStrength=" + eduStrength);
      connStrength += eduStrength * EDUCATION_SCORE;
    }
    

    // compare companies
    if(memberId1.positions != null 
        && memberId2.positions != null &&  memberId1.positions.values != null &&  memberId2.positions.values != null) {

    List<PositionValueObject> positions1 = memberId1.positions.values;
    List<PositionValueObject> positions2 = memberId2.positions.values;

    int positionSize1 = positions1.size();
    int positionSize2 = positions2.size();
    int commonPositionSize = 0;
    for (PositionValueObject position1 : positions1)
    {

      for (PositionValueObject position2 : positions2)
      {
        if (!StringUtils.isBlank(position1.company.name)
            && !StringUtils.isBlank(position2.company.name)
            && position1.company.name.trim()
                                     .equalsIgnoreCase(position2.company.name.trim()))
        {
          commonPositionSize++;
        }
      }

    }

    Double posStrength =
        (new Double(commonPositionSize) / new Double(Math.max(positionSize1,
                                                              positionSize2)));

    System.out.println("posStrength=" + posStrength);

    connStrength += posStrength * POSITION_SCORE;
    }
    // compare skills - 2 cases : if connStrength is 0, if connStrength !=0

    if(memberId1.skills != null 
        && memberId2.skills != null &&  memberId1.skills.values != null &&  memberId2.skills.values != null) {
    List<Values> skills1 = memberId1.skills.values;
    List<Values> skills2 = memberId2.skills.values;

    int skillSize1 = skills1.size();
    int skillSize2 = skills2.size();
    int commonSkillSize = 0;

    for (Values skill1 : skills1)
    {

      for (Values skill2 : skills2)
      {
        if (skill1.skill != null && skill2.skill != null
            && !StringUtils.isBlank(skill1.skill.name)
            && !StringUtils.isBlank(skill2.skill.name)
            && skill1.skill.name.trim().equalsIgnoreCase(skill2.skill.name.trim()))
        {
          commonSkillSize++;
        }
      }

    }

    Double skillStrength =

    (new Double(commonSkillSize) / new Double(Math.max(skillSize1, skillSize2)));

    System.out.println("skillStrength=" + skillStrength);
    connStrength +=
        (connStrength > 0) ? (skillStrength * HIGHER_SKILL_SCORE)
            : (skillStrength * LOWER_SKILL_SCORE);
    }
    return connStrength;
  }
}
