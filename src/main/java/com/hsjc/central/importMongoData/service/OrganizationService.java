package com.hsjc.central.importMongoData.service;

import com.hsjc.central.importMongoData.entity.Organization;
import com.hsjc.central.importMongoData.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;

/**
 * @author : zga
 * @date : 2015-12-17
 *
 */
@Service
public class OrganizationService {
    @Resource
    private OrganizationRepository organizationRepository;

    public void importOrganizationToMysql(){
        Iterable<Organization> iterable = organizationRepository.findAll();

        Iterator iterator = iterable.iterator();
        while(iterator.hasNext()){
            System.out.println((Organization)(iterator.next()));
        }
    }
}
