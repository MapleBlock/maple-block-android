package com.choius323.mapleblock.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.choius323.mapleblock.R
import com.choius323.mapleblock.util.PermissionDescriptionProvider

@Composable
fun PermissionDialog(
    modifier: Modifier = Modifier,
    permissionDescriptionProvider: PermissionDescriptionProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
) {
    val context = LocalContext.current

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        confirmButton = {
            MBPrimaryButton(
                onClick = {
                    if (isPermanentlyDeclined) {
                        onGoToAppSettingsClick()
                    } else {
                        onOkClick()
                    }
                },
                text = stringResource(id = R.string.permission_confirm)
            )
        },
        dismissButton = {
            MBPrimaryButton(
                onClick = { onDismiss() },
                text = stringResource(id = R.string.permission_cancel),
            )
        },
        title = {
            MBText(
                text = permissionDescriptionProvider.getTitle(context),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        text = {
            MBText(
                text = permissionDescriptionProvider.getDescription(
                    context, isPermanentlyDeclined = isPermanentlyDeclined
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    )
}