package com.choius323.mapleblock.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.StringRes
import com.choius323.mapleblock.R

@Suppress("UNCHECKED_CAST")
fun getPermissionMap(permissions: List<String>) =
    permissions.associate {
        it to permissionsMap[it]
    }.filterValues {
        it != null
    } as Map<String, PermissionDescriptionProvider>

@SuppressLint("InlinedApi")
val permissionsMap = mapOf(
    android.Manifest.permission.READ_MEDIA_IMAGES to MediaPermissionDescriptionProvider,
    android.Manifest.permission.READ_MEDIA_VIDEO to MediaPermissionDescriptionProvider,
    android.Manifest.permission.READ_EXTERNAL_STORAGE to ExternalStoragePermissionDescriptionProvider,
    android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED to VisualSelectedPermissionDescriptionProvider,
)

val readImagePermissions =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        listOf(
//            android.Manifest.permission.READ_MEDIA_IMAGES,
//            android.Manifest.permission.READ_MEDIA_VIDEO,
            android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
        )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            android.Manifest.permission.READ_MEDIA_IMAGES,
            android.Manifest.permission.READ_MEDIA_VIDEO
        )
    } else {
        listOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

sealed interface PermissionDescriptionProvider {
    fun getTitle(context: Context): String
    fun getDescription(context: Context, isPermanentlyDeclined: Boolean): String

    fun getDescription(
        context: Context,
        isPermanentlyDeclined: Boolean,
        @StringRes baseDescriptionRes: Int,
        @StringRes permanentlyDescriptionRes: Int,
    ): String {
        val baseDescription =
            context.getString(baseDescriptionRes)
        return if (isPermanentlyDeclined) {
            "$baseDescription\n${context.getString(permanentlyDescriptionRes)}"
        } else {
            baseDescription
        }
    }

    fun getTitle(context: Context, @StringRes titleRes: Int): String {
        return context.getString(titleRes)
    }
}

private object VisualSelectedPermissionDescriptionProvider : PermissionDescriptionProvider {
    override fun getTitle(context: Context) =
        getTitle(context, R.string.permission_dialog_title_visual_selected)

    override fun getDescription(context: Context, isPermanentlyDeclined: Boolean): String =
        getDescription(
            context,
            isPermanentlyDeclined,
            R.string.permission_dialog_description_visual_selected,
            R.string.permission_dialog_description_visual_selected_permanently_declined
        )
}

private object MediaPermissionDescriptionProvider : PermissionDescriptionProvider {
    override fun getTitle(context: Context) =
        getTitle(context, R.string.permission_dialog_title_media)

    override fun getDescription(context: Context, isPermanentlyDeclined: Boolean) =
        getDescription(
            context,
            isPermanentlyDeclined,
            R.string.permission_dialog_description_media,
            R.string.permission_dialog_description_media_permanently_declined
        )
}

private object ExternalStoragePermissionDescriptionProvider : PermissionDescriptionProvider {
    override fun getTitle(context: Context) =
        getTitle(context, R.string.permission_dialog_title_external_storage)


    override fun getDescription(context: Context, isPermanentlyDeclined: Boolean) =
        getDescription(
            context,
            isPermanentlyDeclined,
            R.string.permission_dialog_description_external_storage,
            R.string.permission_dialog_description_external_storage_permanently_declined
        )
}