package com.emergencyguide.Service.Logo;

import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.Rank;

import java.util.List;

public interface PersonalLogoService {
    public List<PersonalLogo> selectAllList(int page, int limit, String searchParams);
    public int selectListCount( String searchParams);
    public int updateById(PersonalLogo personalLogo);
    public  int personalLogoAdd(PersonalLogo personalLogo);
    public  int personalLogoDelete(int id);
    public List<PersonalLogo> selectBasicLogo();
    public List<PersonalLogo> selectSubLogo(String basicLogo);
}
