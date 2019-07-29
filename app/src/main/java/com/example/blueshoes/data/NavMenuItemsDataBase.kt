package com.example.blueshoes.data

import android.content.Context
import com.example.blueshoes.R
import com.example.blueshoes.domain.NavMenuItem

class NavMenuItemsDataBase (context: Context) : List<NavMenuItem> {
    override val size: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun contains(element: NavMenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun containsAll(elements: Collection<NavMenuItem>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(index: Int): NavMenuItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun indexOf(element: NavMenuItem): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEmpty(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): Iterator<NavMenuItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lastIndexOf(element: NavMenuItem): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(): ListIterator<NavMenuItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listIterator(index: Int): ListIterator<NavMenuItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<NavMenuItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val items = listOf(
        NavMenuItem(
            R.id.item_all_shoes.toLong(),
            context.getString(R.string.item_all_shoes)
        ),
        NavMenuItem(
            R.id.item_flip_flops.toLong(),
            context.getString(R.string.item_flip_flops)
        ),
        NavMenuItem(
            R.id.item_cleats.toLong(),
            context.getString(R.string.item_cleats)
        ),
        NavMenuItem(
            R.id.item_sandals.toLong(),
            context.getString(R.string.item_sandals)
        ),
        NavMenuItem(
            R.id.item_ballet_shoes.toLong(),
            context.getString(R.string.item_ballet_shoes)
        ),
        NavMenuItem(
            R.id.item_suit_shoes.toLong(),
            context.getString(R.string.item_suit_shoes)
        ),
        NavMenuItem(
            R.id.item_shoes.toLong(),
            context.getString(R.string.item_shoes)
        ),
        NavMenuItem(
            R.id.item_performance_shoes.toLong(),
            context.getString(R.string.item_performance_shoes)
        ),
        NavMenuItem(
            R.id.item_contact.toLong(),
            context.getString(R.string.item_contact),
            R.drawable.ic_email_black_24dp
        ),
        NavMenuItem(
            R.id.item_about.toLong(),
            context.getString(R.string.item_about),
            R.drawable.ic_domain_black_24dp
        ),
        NavMenuItem(
            R.id.item_privacy_policy.toLong(),
            context.getString(R.string.item_privacy_policy),
            R.drawable.ic_shield_lock_black_24dp
        )
    )

    val itemsLogged = listOf(
        NavMenuItem(
            R.id.item_my_orders.toLong(),
            context.getString(R.string.item_my_orders),
            R.drawable.ic_package_variant_closed_black_24dp
        ),
        NavMenuItem(
            R.id.item_settings.toLong(),
            context.getString(R.string.item_settings),
            R.drawable.ic_settings_black_24dp
        ),
        NavMenuItem(
            R.id.item_sign_out.toLong(),
            context.getString(R.string.item_sign_out),
            R.drawable.ic_exit_run_black_24dp
        )
    )
}