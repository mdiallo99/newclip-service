package com.example.authenticationapp.services;

import com.example.authenticationapp.model.Address;
import com.example.authenticationapp.model.Company;
import com.example.authenticationapp.model.sylobe.Kit;
import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Voucher;
import com.example.authenticationapp.model.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AppServices {


    User addUser(User user);

    List<User> getUsersList();

    User getProfile(String username);

    List<Company> getCompanyList();

    Company addCompany(Company company);

    Company findCompanyByCode(String code);

    Company findCompanyBySocialReason(String socialReason);

    List<Company> saveCompanyListFromSylob(List<Company> companyList);

    List<Address> getAddressList();

    Address addAddress(Address address);

    void deleteAddress(String id);

    Optional<Address> getAddress(String id);

    Set<Article> saveSylobData(Set<Article> objectList);

    Voucher addArticlesInVoucher(Set<Article> object);

    List<Kit> saveKitsFromSylob(Set<Kit> kits);

    Kit findKitByLabel(String label);

    void deleteArticleByCode(String articleCode);

    String addImage(String title, MultipartFile file) throws IOException;
}
