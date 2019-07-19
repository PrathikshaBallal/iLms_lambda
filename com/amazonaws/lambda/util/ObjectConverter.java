package com.amazonaws.lambda.util;

import com.amazonaws.lambda.beans.ProfileBean;
import com.amazonaws.lambda.beans.UserBean;
import com.amazonaws.lambda.domain.LmsUser;
import com.amazonaws.lambda.domain.Profile;

public class ObjectConverter {
   public static LmsUser userBeanToLmsUser(UserBean userBean) {
      if (userBean == null) {
         return null;
      } else {
         LmsUser user = new LmsUser();
         Profile profile = new Profile();
         ProfileBean profileBean = userBean.getProfile();
         user.setId(userBean.getID());
         user.setUserStatus(userBean.getUserStatus());
         profile.setFirstName(profileBean.getF001());
         profile.setMiddleName(profileBean.getF002());
         profile.setLastName(profileBean.getF003());
         profile.setEmployeeId(profileBean.getF004());
         profile.setHireDate(profileBean.getF005());
         profile.setUserLanguage(profileBean.getF006());
         profile.setJobTitle(profileBean.getF007());
         profile.setAddress1(profileBean.getF008());
         profile.setAddress2(profileBean.getF009());
         profile.setCity(profileBean.getF010());
         profile.setCountry(profileBean.getF011());
         profile.setState(profileBean.getF012());
         profile.setProvince(profileBean.getF013());
         profile.setZipCode(profileBean.getF014());
         profile.setEmailId(profileBean.getF015());
         profile.setPassword(profileBean.getF016());
         profile.setPhoneNo(profileBean.getF017());
         profile.setFaxNo(profileBean.getF018());
         profile.setRegion(profileBean.getF019());
         profile.setRegionId(profileBean.getF019_ID());
         profile.setDivision(profileBean.getF020());
         profile.setDivisionId(profileBean.getF020_ID());
         profile.setDepartment(profileBean.getF021());
         profile.setDepartmentId(profileBean.getF021_ID());
         profile.setCompany(profileBean.getF022());
         profile.setTimeZone(profileBean.getF023());
         profile.setCustomField1(profileBean.getF024());
         profile.setCustomField2(profileBean.getF024());
         profile.setCustomField3(profileBean.getF026());
         profile.setCustomField4(profileBean.getF027());
         profile.setRegistrationType(profileBean.getF031());
         profile.setRegistrationDate(profileBean.getF032());
         profile.setSupervisorName(profileBean.getF033());
         profile.setSupervisorEmail(profileBean.getF034());
         user.setProfile(profile);
         return user;
      }
   }
}
