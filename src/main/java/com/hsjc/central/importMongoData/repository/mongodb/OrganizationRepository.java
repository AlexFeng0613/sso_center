package com.hsjc.central.importMongoData.repository.mongodb;

import com.hsjc.central.importMongoData.entity.Organization;
import org.springframework.data.repository.CrudRepository;

/**
 * @author : zga
 * @date : 2015-12-17
 *
 */
//@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {

}
