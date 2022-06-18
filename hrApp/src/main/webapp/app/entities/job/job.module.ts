import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { JobComponent } from './list/job.component';
import { JobDetailComponent } from './detail/job-detail.component';
import { JobUpdateComponent } from './update/job-update.component';
import { JobDeleteDialogComponent } from './delete/job-delete-dialog.component';
import { JobRoutingModule } from './route/job-routing.module';

@NgModule({
  imports: [SharedModule, JobRoutingModule],
  declarations: [JobComponent, JobDetailComponent, JobUpdateComponent, JobDeleteDialogComponent],
  entryComponents: [JobDeleteDialogComponent],
})
export class JobModule {}
