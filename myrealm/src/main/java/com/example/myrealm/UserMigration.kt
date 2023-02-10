package com.example.myrealm

import io.realm.*

class UserMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema: RealmSchema = realm.getSchema();
        var varOldVersion = newVersion
        if (varOldVersion == 1L) {
            schema.get("User")
                ?.addField("id", Int::class.java)
                ?.addField("firstName", String::class.java)
                ?.addField("lastName", String::class.java)
            varOldVersion++
        }
        //User类发生变化，移除lastName，新增个@Required的age字段。
        if (varOldVersion == 2L) {
            schema.get("User")
                //新增@Required的age
                ?.addField("age", String::class.java,FieldAttribute.REQUIRED)
                ?.transform(object : RealmObjectSchema.Function{
                    override fun apply(obj: DynamicRealmObject) {
                        obj.set("age", "1");//为id设置值
                    }

                })
                ?.removeField("lastName")
            varOldVersion++
        }
        //if (oldVersion == 0 && newVersion == 1) {
        //                RealmObjectSchema personSchema = schema.get("User");
        //                //新增@Required的id
        //                personSchema
        //                        .addField("id", String.class, FieldAttribute.REQUIRED)
        //                        .transform(new RealmObjectSchema.Function() {
        //                            @Override
        //                            public void apply(DynamicReal
        //mObject obj) {
        //                                obj.set("id", "1");//为id设置值
        //                            }
        //                        })
        //                        .removeField("age");//移除age属性
        //                oldVersion++;
        //            }
    }
}