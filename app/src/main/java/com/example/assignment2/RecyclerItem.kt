package com.example.assignment2

import android.os.Parcel
import android.os.Parcelable

class RecyclerItem() : Parcelable {
    var id: String? = null
    var title: String? = null
    var content: String? = null
    var modified: String? = null

    constructor(parcel: Parcel?) : this() {
        id = parcel?.readString()
        title = parcel?.readString()
        content = parcel?.readString()
        modified = parcel?.readString()
    }

    constructor(id: String?, title: String?, content: String?, modified: String?) : this() {
        this.id = id
        this.title = title
        this.content = content
        this.modified = modified
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(title)
        dest?.writeString(content)
        dest?.writeString(modified)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR: Parcelable.Creator<RecyclerItem> {
        override fun createFromParcel(source: Parcel?): RecyclerItem {
            return RecyclerItem(source)
        }

        override fun newArray(size: Int): Array<RecyclerItem?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "id: " + this.id + ", title: " + this.title +
                ", content: " + this.content + ", modified: " + this.modified
    }
}