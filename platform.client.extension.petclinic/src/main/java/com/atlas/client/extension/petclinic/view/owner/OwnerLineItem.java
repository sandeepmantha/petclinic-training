package com.atlas.client.extension.petclinic.view.owner;

import java.util.List;

import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.MapsTo;
import com.antheminc.oss.nimbus.domain.defn.MapsTo.Path;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Grid;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridColumn;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.GridRowBody;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.Link;
import com.antheminc.oss.nimbus.domain.defn.ViewConfig.LinkMenu;
import com.antheminc.oss.nimbus.domain.defn.extension.Content.Label;
import com.atlas.client.extension.petclinic.core.Owner;

import lombok.Getter;
import lombok.Setter;

@MapsTo.Type(Owner.class)
@Getter @Setter
public class OwnerLineItem {
 
	@GridColumn(hidden = true)
    @Path
    private Long id;
 
    @Label("First Name")
    @GridColumn
    @Path
    private String firstName;
 
    @Label("Last Name")
    @GridColumn
    @Path
    private String lastName;
 
    @Label("Owner City")
    @GridColumn
    @Path("city")
    private String ownerCity;
 
    @Label("Telephone")
    @GridColumn
    @Path
    private String telephone;   
    
    @LinkMenu
    private VLMCaseItemLinks vlmCaseItemLinks;
   
    @GridRowBody
    private ExpandedRowContent expandedRowContent;
    
    @Model @Getter @Setter
    public static class ExpandedRowContent {
    	
    	@Label("Pets")
    	@Grid(onLoad = true)
    	@Path(linked = false)
		@Config(url="<!#this!>.m/_process?fn=_set&url=/p/pet/_search?fn=query&where=pet.ownerId.eq(<!/../.m/id!>)")
        private List<PetLineItemOwnerLanding> pets;
    }
    
    @Model @Getter @Setter
    public static class VLMCaseItemLinks {
        
    	@Label("Edit")
        @Link(imgSrc = "edit.png")
    	@Config(url = "/p/ownerview:<!/../id!>/_get")
    	private String edit;
     
    	@Label("Owner Info")
        @Link(imgSrc = "task.svg")
    	@Config(url = "/p/ownerview:<!/../id!>/_get")
        @Config(url = "/p/ownerview:<!/../id!>/_nav?pageId=vpOwnerInfo")
        private String ownerInfo;
    }
}