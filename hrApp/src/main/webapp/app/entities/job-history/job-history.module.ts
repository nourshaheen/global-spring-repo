import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { JobHistoryComponent } from './list/job-history.component';
import { JobHistoryDetailComponent } from './detail/job-history-detail.component';
import { JobHistoryUpdateComponent } from './update/job-history-update.component';
import { JobHistoryDeleteDialogComponent } from './delete/job-history-delete-dialog.component';
import { JobHistoryRoutingModule } from './route/job-history-routing.module';

@NgModule({
  imports: [SharedModule, JobHistoryRoutingModule],
  declarations: [JobHistoryComponent, JobHistoryDetailComponent, JobHistoryUpdateComponent, JobHistoryDeleteDialogComponent],
  entryComponents: [JobHistoryDeleteDialogComponent],
})
export class JobHistoryModule {}
