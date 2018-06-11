package com.pp.activejdbc.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;

@Table("u2base")
//@BelongsToParents({ 
//@BelongsTo(foreignKeyName="id",parent=User.class), 
//@BelongsTo(foreignKeyName="id",parent=Movie.class) 
//}) 
public class U2Base extends Model {}
