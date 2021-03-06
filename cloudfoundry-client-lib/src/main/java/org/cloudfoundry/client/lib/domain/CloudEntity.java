/*
 * Copyright 2009-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.client.lib.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;
import java.util.UUID;

/**
 * @author Thomas Risberg
 */
public class CloudEntity {

	@JsonIgnore
	private Meta meta;

	private String name;

	public CloudEntity() {
	}

	public CloudEntity(Meta meta, String name) {
		// This constructor should be used by any V2 entities.
		// For V1 entities pass in a null meta parameter and it will be set
		// to V1 default value.
		if (meta != null) {
			this.meta = meta;
		}
		else {
			this.meta = Meta.defaultV1Meta();
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": (" + (meta == null ? "v1" : meta.getGuid()) + ") " + getName();
	}

	public static class Meta {

		private UUID guid;
		private Date created;
		private Date updated;
		private int version;

		public Meta(UUID guid, Date created, Date updated, int version) {
			this.guid = guid;
			this.created = created;
			this.updated = updated;
			this.version = version;
		}

		public UUID getGuid() {
			return guid;
		}

		public Date getCreated() {
			return created;
		}

		public Date getUpdated() {
			return updated;
		}

		public int getVersion() {
			return version;
		}

		public static Meta defaultV1Meta() {
			return new Meta(null, null, null, 1);
		}

		public static Meta defaultV2Meta() {
			return new Meta(null, null, null, 2);
		}
	}
}
