import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerSupportProvider
import com.intellij.platform.lsp.api.ProjectWideLspServerDescriptor


class RuffLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(project: Project, file: VirtualFile, serverStarter: LspServerSupportProvider.LspServerStarter) {
        if (file.extension == "py") {
            serverStarter.ensureServerStarted(RuffLspServerDescriptor(project))
        }
    }
}


private class RuffLspServerDescriptor(project: Project) : ProjectWideLspServerDescriptor(project, "Ruff") {
    override fun isSupportedFile(file: VirtualFile) = file.extension == "py"
    override fun createCommandLine() = GeneralCommandLine("ruff-lsp")
}