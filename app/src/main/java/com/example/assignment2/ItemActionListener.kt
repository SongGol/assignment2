package com.example.assignment2

interface ItemActionListener {
    fun onItemMoved(from: Int, to: Int)
    fun onItemSwipped(position: Int)
}