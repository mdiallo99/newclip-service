package com.example.authenticationapp.services;

import com.example.authenticationapp.model.Address;
import com.example.authenticationapp.model.Company;
import com.example.authenticationapp.model.sylobe.Article;
import com.example.authenticationapp.model.sylobe.Kit;
import com.example.authenticationapp.model.sylobe.Photo;
import com.example.authenticationapp.model.sylobe.Voucher;
import com.example.authenticationapp.model.user.User;
import com.example.authenticationapp.repository.*;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AppServicesImpl implements AppServices {

    /**
     * This class handle all database requests. It contains all our database documents
     */
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final ArticleRepository articleRepository;
    private final VoucherRepository voucherRepository;
    private final KitRepository kitRepository;
    private final PhotoRepository photoRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    public User getProfile(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public List<Company> getCompanyList() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company findCompanyByCode(String code) {
        return companyRepository.findCompanyCode(code);
    }

    @Override
    public Company findCompanyBySocialReason(String socialReason) {
        return companyRepository.findCompanyBySocialReason(socialReason);
    }

    @Override
    public List<Company> saveCompanyListFromSylob(List<Company> companyList) {
        companyRepository.deleteAll();
        return  companyRepository.saveAll(companyList);
    }


    @Override
    public List<Address> getAddressList() {
        return addressRepository.findAll();
    }

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(String id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Optional<Address> getAddress(String id) {
        return addressRepository.findById(id);
    }

    @Override
    public Set<Article> saveSylobData(Set<Article> objectList) {
        articleRepository.deleteAll();
        articleRepository.saveAll(objectList);
        return objectList;
    }

    @Override
    public Voucher addArticlesInVoucher(Set<Article> article) {
        Voucher voucher = new Voucher("Description");
        voucher.setArticles(article);
        return voucherRepository.save(voucher);
    }

    @Override
    public List<Kit> saveKitsFromSylob(Set<Kit> kits) {
        kitRepository.deleteAll();
        return kitRepository.saveAll(kits);
    }

    @Override
    public Kit findKitByLabel(String label) {
        return kitRepository.findByLabel(label);
    }

    @Override
    public void deleteArticleByCode(String articleCode) {
        articleRepository.deleteArticleByArticleCode(articleCode);
    }

    @Override
    public String addImage(String name, MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setName(name);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepository.insert(photo);
        return photo.getId();
    }

}
