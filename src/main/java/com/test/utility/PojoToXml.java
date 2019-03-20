package com.test.utility;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.test.model.MemberSnapshot;

public class PojoToXml {
		public static MemberSnapshot convert(Object member_snap) throws JsonGenerationException, JsonMappingException, IOException {
			XmlMapper mapper = new XmlMapper();
			mapper.writeValue(new File("snapshot.xml"), member_snap);
			System.out.println("Object serialized into XML ");
			return mapper.readValue(new File("snapshot.xml"), MemberSnapshot.class);
			
		}
}
