package com.pankajapps.groceriesrestfulapi.retail.entity

import com.pankajapps.groceriesrestfulapi.user.entity.AppUser
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "shopping_list")
class ShoppingList(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopping_list_id_seq")
    @SequenceGenerator(name = "shopping_list_id_seq", sequenceName = "shopping_list_id_seq", allocationSize = 1)
    val  id: Long = 0,

    var isDone: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    var appUser: AppUser? = null
) {

}