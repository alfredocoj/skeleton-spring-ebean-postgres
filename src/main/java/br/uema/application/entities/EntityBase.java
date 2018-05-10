package br.uema.application.entities;

import br.uema.application.util.ValidatorEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.ebean.Model;
import io.ebean.annotation.SoftDelete;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import org.apache.commons.lang3.NotImplementedException;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.util.Set;

@MappedSuperclass
public class EntityBase extends Model {
        protected Long version;

        @WhenCreated
        @Column(name = "created_at")
        protected Timestamp created;

        @WhenModified
        @Column (name = "updated_at")
        protected Timestamp updated;

        @SoftDelete
        private boolean deleted;

        public Integer getId() {
            throw new NotImplementedException("Not implemented");
        }

        @Override
        public void save() throws ValidationException {
            validate();
            forceSave();
        }

        public void forceSave() {
            super.save();
        }

        @Override
        public void update() throws ValidationException {
            validate();
            forceUpdate();
        }

        public void forceUpdate() {
            super.update();
        }

        protected void validate() throws ValidationException {
            Set<ConstraintViolation<EntityBase>> validator = ValidatorEntity.validate(this);

            if (!validator.isEmpty()) {
                ConstraintViolation<EntityBase> error = validator.iterator().next();
                String campo = error.getPropertyPath().toString();
                throw new ValidationException(campo.concat(" - ").concat(error.getMessage()));
            }
        }

        public Long getVersion() {
            return version;
        }

        public void setVersion(Long version) {
            this.version = version;
        }

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty("created_at")
        public Timestamp getCreated() {
            return created;
        }

        public void setCreated(Timestamp created) {
            this.created = created;
        }

        @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty ("updated_at")
        public Timestamp getUpdated() {
            return updated;
        }

        public void setUpdated(Timestamp updated) {
            this.updated = updated;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }
}